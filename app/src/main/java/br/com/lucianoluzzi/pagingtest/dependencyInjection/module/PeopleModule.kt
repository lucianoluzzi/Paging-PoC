package br.com.lucianoluzzi.pagingtest.dependencyInjection.module

import android.app.Application
import androidx.room.Room
import br.com.lucianoluzzi.pagingtest.repository.PeopleRepository
import br.com.lucianoluzzi.pagingtest.repository.PeopleRepositoryImpl
import br.com.lucianoluzzi.pagingtest.repository.network.RemotePeopleRepositoryImpl
import br.com.lucianoluzzi.pagingtest.repository.network.PeopleService
import br.com.lucianoluzzi.pagingtest.repository.network.WebClient
import br.com.lucianoluzzi.pagingtest.repository.room.PeopleDAO
import br.com.lucianoluzzi.pagingtest.repository.room.LocalPeopleRepositoryImpl
import br.com.lucianoluzzi.pagingtest.repository.room.PeopleRoomDatabase
import br.com.lucianoluzzi.pagingtest.repository.room.PeopleRoomDatabase.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton


@Module
class PeopleModule
    (
    application: Application
) {
    private val database: PeopleRoomDatabase = Room.databaseBuilder(
        application.applicationContext,
        PeopleRoomDatabase::class.java, DATABASE_NAME
    ).build()

    @Provides
    @Reusable
    fun provideArticleService(): PeopleService {
        return WebClient().service()
    }

    @Singleton
    @Provides
    fun provideRoomDatabase(): PeopleRoomDatabase {
        return database
    }

    @Reusable
    @Provides
    fun providePeopleDao(): PeopleDAO {
        return database.peopleDao()
    }

    @Reusable
    @Provides
    fun provideLocalRepository(peopleDAO: PeopleDAO): LocalPeopleRepositoryImpl {
        return LocalPeopleRepositoryImpl(peopleDAO)
    }

    @Reusable
    @Provides
    fun provideRemoteRepository(service: PeopleService): RemotePeopleRepositoryImpl {
        return RemotePeopleRepositoryImpl(service)
    }

    @Reusable
    @Provides
    fun provideRepository(
        localPeopleRepository: LocalPeopleRepositoryImpl,
        remotePeopleRepository: RemotePeopleRepositoryImpl
    ): PeopleRepository {
        return PeopleRepositoryImpl(localPeopleRepository, remotePeopleRepository)
    }
}