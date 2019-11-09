package br.com.lucianoluzzi.pagingtest.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import br.com.lucianoluzzi.pagingtest.R
import br.com.lucianoluzzi.pagingtest.ui.viewModel.PeopleViewModel
import br.com.lucianoluzzi.pagingtest.ui.viewModel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
