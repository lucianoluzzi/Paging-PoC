package br.com.lucianoluzzi.pagingtest.ui.view

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.runner.AndroidJUnit4
import br.com.lucianoluzzi.pagingtest.ui.viewModel.PeopleViewModel
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock

@RunWith(AndroidJUnit4::class)
class PeopleListFragmentTest {

    @Mock
    lateinit var viewModel: PeopleViewModel

    @Before
    fun setUp() {
        val scenario = launchFragmentInContainer<PeopleListFragment>()
    }
}