package br.com.lucianoluzzi.pagingtest.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.lucianoluzzi.pagingtest.model.entity.Person
import br.com.lucianoluzzi.pagingtest.repository.room.PeopleRoomDatabase.Companion.DATABASE_VERSION

@Database(entities = [Person::class], version = DATABASE_VERSION)
abstract class PeopleRoomDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "people_database"
    }

    abstract fun peopleDao(): PeopleDAO
}