package br.com.lucianoluzzi.pagingtest.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.lucianoluzzi.pagingtest.model.entity.Person
import br.com.lucianoluzzi.pagingtest.repository.MockPeopleDataProvider.Companion.getMockedPeople
import br.com.lucianoluzzi.pagingtest.repository.PeopleRepository
import br.com.lucianoluzzi.pagingtest.ui.viewModel.PeopleViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class PeopleViewModelTest {
    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var peopleRepository: PeopleRepository
    private lateinit var viewModel: PeopleViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = PeopleViewModel(peopleRepository)
    }

    @Test
    fun `test people live data initial value should be empty list`() {
        viewModel.people.value?.let {
            assertEquals(it, listOf<Person>())
        } ?: run {
            fail("Expected list to be empty, list was null")
        }
    }

    @Test
    fun `fetchPeople with null response should post null`() = runBlocking {
        `when`(peopleRepository.fetchPeople()).thenReturn(null)

        viewModel.fetchPeople()

        assertNull(viewModel.people.value)
    }

    @Test
    fun `fetchPeople with mocked data should return mocked data`() = runBlocking {
        val mockedData = getMockedPeople()

        `when`(peopleRepository.fetchPeople()).thenReturn(mockedData)
        viewModel.fetchPeople()

        assertEquals(mockedData, viewModel.people.value)
    }
}