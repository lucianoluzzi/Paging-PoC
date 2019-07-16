package br.com.lucianoluzzi.pagingtest.repository.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.lucianoluzzi.pagingtest.model.entity.Person

@Dao
interface PeopleDAO {
    companion object {
        const val GET_ALL = "SELECT * FROM ${Person.PERSON_TABLE_NAME} ORDER BY name desc"
        const val GET_PERSON = "SELECT COUNT(1) FROM ${Person.PERSON_TABLE_NAME} WHERE uid = :uid"
    }

    @Query(GET_ALL)
    fun fetchPeople(): List<Person>?

    @Query(GET_PERSON)
    fun containsPerson(uid: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPerson(person: Person)
}