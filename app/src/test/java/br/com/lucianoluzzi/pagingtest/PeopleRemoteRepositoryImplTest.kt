package br.com.lucianoluzzi.pagingtest

import br.com.lucianoluzzi.pagingtest.model.dto.PeopleResponse
import br.com.lucianoluzzi.pagingtest.model.entity.Person
import br.com.lucianoluzzi.pagingtest.repository.network.PeopleRemoteRepositoryImpl
import br.com.lucianoluzzi.pagingtest.repository.network.PeopleService
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Response

class PeopleRemoteRepositoryImplTest {
    @Mock
    lateinit var peopleService: PeopleService
    private lateinit var repository: PeopleRemoteRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = PeopleRemoteRepositoryImpl(peopleService)
    }

    @Test
    fun `fetchPeople with call exception should return null`() {
        `when`(peopleService.fetchPeople()).thenReturn(null)
        assertNull(repository.fetchPeople())
    }

    @Test
    fun `fetchPeople with response body null should return null`() {
        val response: Response<PeopleResponse> = Response.success(null)
        val mockCall = mock<Call<PeopleResponse>> {
            on { execute() } doReturn response
        }

        `when`(peopleService.fetchPeople()).thenReturn(mockCall)

        assertNull(repository.fetchPeople())
    }

    @Test
    fun `fetchPeople with mock response should return mock response`() {
        val peopleResponse = PeopleResponse(getMockedPeople())
        val response: Response<PeopleResponse> = Response.success(peopleResponse)
        val mockCall = mock<Call<PeopleResponse>> {
            on { execute() } doReturn response
        }

        `when`(peopleService.fetchPeople()).thenReturn(mockCall)

        assertEquals(peopleResponse.people, repository.fetchPeople())
    }

    private fun getMockedPeople(): List<Person> {
        val luciano = Person(
            1,
            "luciano",
            "",
            "messi"
        )
        val virginia = Person(
            2,
            "virginia",
            "tagliafico",
            "jesus"
        )
        val zuleica = Person(
            2,
            "cordova",
            "",
            "cordova"
        )

        return listOf(luciano, virginia, zuleica)
    }
}