package br.com.lucianoluzzi.pagingtest.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.lucianoluzzi.pagingtest.model.entity.UIResponseState
import br.com.lucianoluzzi.pagingtest.repository.PeopleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PeopleViewModel @Inject constructor(
    private val repository: PeopleRepository
) : ViewModel() {

    private val _viewState = MutableLiveData<UIResponseState>().apply {
        value = UIResponseState.Loading
    }
    val viewState: LiveData<UIResponseState> = _viewState

    suspend fun fetchPeople() = withContext(Dispatchers.IO) {

        val people = repository.fetchPeople()
        people?.let {
            _viewState.postValue(UIResponseState.Success(it))
        } ?: run {
            _viewState.postValue(UIResponseState.Error("Check your connection"))
        }
    }
}