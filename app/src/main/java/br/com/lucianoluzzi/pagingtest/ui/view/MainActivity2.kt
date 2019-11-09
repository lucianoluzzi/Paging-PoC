package br.com.lucianoluzzi.pagingtest.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewModelScope
import br.com.lucianoluzzi.pagingtest.App
import br.com.lucianoluzzi.pagingtest.R
import br.com.lucianoluzzi.pagingtest.databinding.ActivityMain2Binding
import br.com.lucianoluzzi.pagingtest.ui.viewModel.PeopleViewModel2
import kotlinx.coroutines.launch

class MainActivity2 : AppCompatActivity() {
    private val peopleViewModel: PeopleViewModel2 by lazy {
        ViewModelProviders.of(this, (application as App).component().peopleViewModel2())
            .get(PeopleViewModel2::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMain2Binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main2
        )

        with(binding) {
            viewModel = peopleViewModel
            lifecycleOwner = this@MainActivity2
        }
    }

    override fun onStart() {
        super.onStart()
        fetchPeople()
    }

    private fun fetchPeople() {
        with(peopleViewModel) {
            viewModelScope.launch {
                fetchPeople()
            }
        }
    }
}
