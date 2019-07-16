package br.com.lucianoluzzi.pagingtest.repository.network

import android.util.Log
import br.com.lucianoluzzi.pagingtest.model.entity.Person
import br.com.lucianoluzzi.pagingtest.repository.PeopleRepository
import javax.inject.Inject

class PeopleRemoteRepositoryImpl @Inject constructor(
    private val peopleService: PeopleService
) : PeopleRepository {

    override fun fetchPeople(): List<Person>? {
        val response = peopleService.fetchPeople()
        return try {
            response.execute().body()?.people
        } catch (exception: Exception) {
            Log.d("REMOTE_REPOSITORY", exception.message)
            null
        }
    }
}