package br.com.lucianoluzzi.pagingtest.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewModelScope
import br.com.lucianoluzzi.pagingtest.App
import br.com.lucianoluzzi.pagingtest.R
import br.com.lucianoluzzi.pagingtest.databinding.ActivityMainBinding
import br.com.lucianoluzzi.pagingtest.ui.viewModel.PeopleViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val peopleViewModel: PeopleViewModel by lazy {
        ViewModelProviders.of(this, (application as App).component().peopleViewModel())
            .get(PeopleViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        with(binding) {
            viewModel = peopleViewModel
            lifecycleOwner = this@MainActivity
        }
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
