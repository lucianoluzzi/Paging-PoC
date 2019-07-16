package br.com.lucianoluzzi.pagingtest.repository.room

import br.com.lucianoluzzi.pagingtest.model.entity.Person
import br.com.lucianoluzzi.pagingtest.repository.PeopleRepository
import javax.inject.Inject

class PeopleLocalRepositoryImpl @Inject constructor(
    private val peopleDAO: PeopleDAO
) : PeopleRepository {

    override fun fetchPeople(): List<Person>? {
        return peopleDAO.fetchPeople()
    }

    fun containsPerson(uid: Int): Boolean {
        return peopleDAO.containsPerson(uid) == 1
    }

    fun insertPerson(person: Person) {
        peopleDAO.insertPerson(person)
    }
}