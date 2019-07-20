package br.com.lucianoluzzi.pagingtest.repository.room

import br.com.lucianoluzzi.pagingtest.model.entity.Person
import br.com.lucianoluzzi.pagingtest.repository.PeopleRepository

interface LocalPeopleRepository : PeopleRepository {
    fun containsPerson(uid: Int): Boolean

    fun insertPerson(person: Person)
}