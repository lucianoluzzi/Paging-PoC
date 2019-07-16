package br.com.lucianoluzzi.pagingtest.repository.network

import br.com.lucianoluzzi.pagingtest.model.dto.PeopleResponse
import retrofit2.Call
import retrofit2.http.GET

interface PeopleService {
    @GET("/names")
    fun fetchPeople(): Call<PeopleResponse>
}