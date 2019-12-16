package br.com.lucianoluzzi.pagingtest.ui.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewModelScope
import br.com.lucianoluzzi.pagingtest.App
import br.com.lucianoluzzi.pagingtest.R
import br.com.lucianoluzzi.pagingtest.databinding.ActivityMainWithoutBindingAdaptersBinding
import br.com.lucianoluzzi.pagingtest.model.entity.Person
import br.com.lucianoluzzi.pagingtest.model.entity.UIResponseState
import br.com.lucianoluzzi.pagingtest.ui.isInstanceOf
import br.com.lucianoluzzi.pagingtest.ui.view.items.PeopleAdapter
import br.com.lucianoluzzi.pagingtest.ui.viewModel.PeopleViewModel
import kotlinx.coroutines.launch

class MainActivityWithoutBindingAdapters : AppCompatActivity() {
    private val peopleViewModel: PeopleViewModel by lazy {
        ViewModelProviders.of(this, (application as App).component().peopleViewModel())
            .get(PeopleViewModel::class.java)
    }

    private lateinit var binding: ActivityMainWithoutBindingAdaptersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main_without_binding_adapters
        )

        observeStates()
    }

    private fun observeStates() {
        peopleViewModel.viewState.observe(this, Observer { uiState ->
            with(binding) {
                when (uiState) {
                    is UIResponseState.Loading -> {
                        progress.visibility = View.VISIBLE
                        people.visibility = View.GONE
                        error.visibility = View.GONE
                    }
                    is UIResponseState.Error -> {
                        error.visibility = View.VISIBLE
                        progress.visibility = View.GONE
                        people.visibility = View.GONE
                        error.text = uiState.errorMessage
                    }
                    is UIResponseState.Success<*> -> {
                        people.visibility = View.VISIBLE
                        progress.visibility = View.GONE
                        error.visibility = View.GONE

                        // set the recyclerView with the content
                        if (isInstanceOf<UIResponseState.Success<List<Person>>>(uiState))
                            setRecyclerView(uiState as UIResponseState.Success<List<Person>>)
                    }
                }
            }
        })
    }

    private fun setRecyclerView(response: UIResponseState.Success<List<Person>>) {
        binding.people.adapter = PeopleAdapter(this, response.content)
    }

    override fun onStart() {
        super.onStart()
        fetchPeople()
    }

    private fun fetchPeople() {
        peopleViewModel.viewModelScope.launch {
            peopleViewModel.fetchPeople()
        }
    }
}