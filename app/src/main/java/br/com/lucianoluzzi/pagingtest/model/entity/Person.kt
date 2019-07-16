package br.com.lucianoluzzi.pagingtest.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_table")
data class Person(
    @PrimaryKey
    val uid: Int,
    val name: String,
    val middleName: String,
    val lastName: String
) {
    companion object {
        const val PERSON_TABLE_NAME = "person_table"
    }

    fun getPerson(): Person {
        return this
    }
}