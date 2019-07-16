package br.com.lucianoluzzi.pagingtest.repository

import br.com.lucianoluzzi.pagingtest.model.entity.Person

interface PeopleRepository {
    fun fetchPeople(): List<Person>?
}