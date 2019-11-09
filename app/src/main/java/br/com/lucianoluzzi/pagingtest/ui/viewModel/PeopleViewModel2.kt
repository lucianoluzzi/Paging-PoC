package br.com.lucianoluzzi.pagingtest.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.lucianoluzzi.pagingtest.model.entity.Person
import br.com.lucianoluzzi.pagingtest.model.entity.UIResponseState
import br.com.lucianoluzzi.pagingtest.repository.PeopleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PeopleViewModel2 @Inject constructor(
    private val repository: PeopleRepository
) : ViewModel() {
    private val mPeople: MutableLiveData<List<Person>> = MutableLiveData<List<Person>>().apply {
        value = listOf()
    }
    val people: LiveData<List<Person>> = mPeople
    private val _viewState = MutableLiveData<UIResponseState>().apply {
        value = UIResponseState.Loading
    }
    val viewState: LiveData<UIResponseState> = _viewState

    suspend fun fetchPeople() = withContext(Dispatchers.IO) {
        _viewState.postValue(UIResponseState.Loading)

        val people = repository.fetchPeople()
        people?.let {
            _viewState.postValue(UIResponseState.Success(people))
        } ?: run {
            _viewState.postValue(UIResponseState.Error("Check your connection"))
        }
    }
}