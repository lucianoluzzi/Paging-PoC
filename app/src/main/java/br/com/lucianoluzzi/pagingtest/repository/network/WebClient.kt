package br.com.lucianoluzzi.pagingtest.repository.network

import br.com.lucianoluzzi.pagingtest.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WebClient {
    private val retrofit: Retrofit = retrofit()
    private fun retrofit() = with(Retrofit.Builder()) {
        baseUrl(BuildConfig.HOST_URL)
        addConverterFactory(GsonConverterFactory.create())
        build()
    }

    fun service(): PeopleService = retrofit.create(PeopleService::class.java)
}