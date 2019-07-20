package br.com.lucianoluzzi.pagingtest.repository

import br.com.lucianoluzzi.pagingtest.repository.MockPeopleDataProvider.Companion.getMockedPeople
import br.com.lucianoluzzi.pagingtest.repository.network.RemotePeopleRepository
import br.com.lucianoluzzi.pagingtest.repository.room.LocalPeopleRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class PeopleRepositoryImplTest {
    @Mock
    lateinit var localRepository: LocalPeopleRepository
    @Mock
    lateinit var remoteRepository: RemotePeopleRepository

    private val repository: PeopleRepositoryImpl by lazy {
        PeopleRepositoryImpl(
            localRepository = localRepository,
            remoteRepository = remoteRepository
        )
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `fetchPeople with both repositories returning null should return null`() {
        `when`(remoteRepository.fetchPeople()).thenReturn(null)
        `when`(localRepository.fetchPeople()).thenReturn(null)

        assertNull(repository.fetchPeople())
    }

    @Test
    fun `fetchPeople with both repositories returning null verify both called`() {
        `when`(remoteRepository.fetchPeople()).thenReturn(null)
        `when`(localRepository.fetchPeople()).thenReturn(null)

        repository.fetchPeople()

        verify(remoteRepository).fetchPeople()
        verify(localRepository).fetchPeople()
    }

    @Test
    fun `fetchPeople with remote repository returning null should return local mocked data`() {
        val mockedData = getMockedPeople()

        `when`(remoteRepository.fetchPeople()).thenReturn(null)
        `when`(localRepository.fetchPeople()).thenReturn(mockedData)

        assertEquals(mockedData, repository.fetchPeople())
    }

    @Test
    fun `fetchPeople with remote repository returning null verify local called`() {
        val mockedData = getMockedPeople()

        `when`(remoteRepository.fetchPeople()).thenReturn(null)
        `when`(localRepository.fetchPeople()).thenReturn(mockedData)

        repository.fetchPeople()

        verify(localRepository).fetchPeople()
    }

    @Test
    fun `fetchPeople with remote repository returning mocked data should return mocked data`() {
        val mockedData = getMockedPeople()

        `when`(remoteRepository.fetchPeople()).thenReturn(mockedData)

        assertEquals(mockedData, repository.fetchPeople())
    }

    @Test
    fun `fetchPeople with remote repository returning mocked data verify remote called`() {
        val mockedData = getMockedPeople()

        `when`(remoteRepository.fetchPeople()).thenReturn(mockedData)
        repository.fetchPeople()

        verify(remoteRepository).fetchPeople()
    }
}