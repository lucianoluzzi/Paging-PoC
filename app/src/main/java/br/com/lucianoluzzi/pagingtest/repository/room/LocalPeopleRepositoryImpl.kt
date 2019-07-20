package br.com.lucianoluzzi.pagingtest.repository.room

import br.com.lucianoluzzi.pagingtest.model.entity.Person
import javax.inject.Inject

class LocalPeopleRepositoryImpl @Inject constructor(
    private val peopleDAO: PeopleDAO
) : LocalPeopleRepository {

    override fun fetchPeople(): List<Person>? {
        return peopleDAO.fetchPeople()
    }

    override fun containsPerson(uid: Int): Boolean {
        return peopleDAO.containsPerson(uid) == 1
    }

    override fun insertPerson(person: Person) {
        peopleDAO.insertPerson(person)
    }
}