package br.com.lucianoluzzi.pagingtest.repository.network

import br.com.lucianoluzzi.pagingtest.model.entity.Person
import javax.inject.Inject

class RemotePeopleRepositoryImpl @Inject constructor(
    private val peopleService: PeopleService
) : RemotePeopleRepository {

    override fun fetchPeople(): List<Person>? {
        val response = peopleService.fetchPeople()
        return try {
            response.execute().body()?.people
        } catch (exception: Exception) {
            null
        }
    }
}