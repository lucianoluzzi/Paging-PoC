package br.com.lucianoluzzi.pagingtest.repository.room

import br.com.lucianoluzzi.pagingtest.repository.MockPeopleDataProvider.Companion.getMockedPeople
import br.com.lucianoluzzi.pagingtest.repository.MockPeopleDataProvider.Companion.getMockedPerson
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class PeopleLocalRepositoryImplTest {
    @Mock
    lateinit var peopleDAO: PeopleDAO
    private lateinit var repository: PeopleLocalRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = PeopleLocalRepositoryImpl(peopleDAO)
    }

    @Test
    fun `fetchPeople with null response data should return null`() {
        `when`(peopleDAO.fetchPeople()).thenReturn(null)

        assertNull(repository.fetchPeople())
    }

    @Test
    fun `fetchPeople with mocked response data should return mocked response data`() {
        val expectedReturn = getMockedPeople()

        `when`(peopleDAO.fetchPeople()).thenReturn(expectedReturn)

        assertEquals(expectedReturn, repository.fetchPeople())
    }

    @Test
    fun `containsPerson should with count 1 should return true`() {
        val uid = 10

        `when`(peopleDAO.containsPerson(uid)).thenReturn(1)

        assertTrue(repository.containsPerson(uid))
    }

    @Test
    fun `containsPerson should with count 0 should return false`() {
        val uid = 10

        `when`(peopleDAO.containsPerson(uid)).thenReturn(0)

        assertFalse(repository.containsPerson(uid))
    }

    @Test
    fun `insertPerson should pass received data to DAO`() {
        val expectedInsertion = getMockedPerson()

        repository.insertPerson(expectedInsertion)

        verify(peopleDAO).insertPerson(expectedInsertion)
    }
}