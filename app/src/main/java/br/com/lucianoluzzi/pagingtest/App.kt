package br.com.lucianoluzzi.pagingtest

import android.app.Application
import br.com.lucianoluzzi.pagingtest.dependencyInjection.component.DaggerPeopleComponent
import br.com.lucianoluzzi.pagingtest.dependencyInjection.component.PeopleComponent
import br.com.lucianoluzzi.pagingtest.dependencyInjection.module.PeopleModule

class App : Application() {
    private val component: PeopleComponent by lazy {
        DaggerPeopleComponent
            .builder()
            .peopleModule(PeopleModule(this))
            .build()
    }

    fun component(): PeopleComponent = component
}