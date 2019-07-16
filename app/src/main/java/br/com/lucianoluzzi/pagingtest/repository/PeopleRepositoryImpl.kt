package br.com.lucianoluzzi.pagingtest.repository

import br.com.lucianoluzzi.pagingtest.model.entity.Person
import br.com.lucianoluzzi.pagingtest.repository.network.PeopleRemoteRepositoryImpl
import br.com.lucianoluzzi.pagingtest.repository.room.PeopleLocalRepositoryImpl
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(
    private val localRepository: PeopleLocalRepositoryImpl,
    private val remoteRepository: PeopleRemoteRepositoryImpl
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