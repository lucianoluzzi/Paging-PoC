package br.com.lucianoluzzi.pagingtest.dependencyInjection.component

import br.com.lucianoluzzi.pagingtest.dependencyInjection.module.PeopleModule
import br.com.lucianoluzzi.pagingtest.ui.viewModel.PeopleViewModel
import br.com.lucianoluzzi.pagingtest.ui.viewModel.PeopleViewModel2
import br.com.lucianoluzzi.pagingtest.ui.viewModel.ViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [PeopleModule::class])
interface PeopleComponent {
    fun peopleViewModel(): ViewModelFactory<PeopleViewModel>
    fun peopleViewModel2(): ViewModelFactory<PeopleViewModel2>
}