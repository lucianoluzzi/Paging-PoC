package br.com.lucianoluzzi.pagingtest.repository

import br.com.lucianoluzzi.pagingtest.model.entity.Person
import br.com.lucianoluzzi.pagingtest.repository.network.RemotePeopleRepository
import br.com.lucianoluzzi.pagingtest.repository.room.LocalPeopleRepository
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(
    private val localRepository: LocalPeopleRepository,
    private val remoteRepository: RemotePeopleRepository
) : PeopleRepository {

    override fun fetchPeople(): List<Person>? {
        val fetchPeople = remoteRepository.fetchPeople()

        fetchPeople?.let {
            persistData(it)
            return it
        } ?: run {
            return localRepository.fetchPeople()
        }
    }

    private fun persistData(people: List<Person>) {
        people.forEach {
            if (!localRepository.containsPerson(it.uid))
                localRepository.insertPerson(it)
        }
    }
}