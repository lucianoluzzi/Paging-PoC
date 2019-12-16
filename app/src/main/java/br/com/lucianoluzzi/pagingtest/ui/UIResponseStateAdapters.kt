package br.com.lucianoluzzi.pagingtest.ui

import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.lucianoluzzi.pagingtest.model.entity.Person
import br.com.lucianoluzzi.pagingtest.model.entity.UIResponseState
import br.com.lucianoluzzi.pagingtest.ui.view.items.PeopleAdapter

@BindingAdapter("hideOnLoading")
fun ViewGroup.hideOnLoading(responseState: UIResponseState) {
    visibility = if (responseState is UIResponseState.Loading)
        View.GONE
    else
        View.VISIBLE
}

@BindingAdapter("showOnLoading")
fun ProgressBar.showOnLoading(responseState: UIResponseState) {
    visibility = if (responseState is UIResponseState.Loading)
        View.VISIBLE
    else
        View.GONE
}

@BindingAdapter("showOnError")
fun TextView.showError(responseState: UIResponseState) {
    visibility = if (responseState is UIResponseState.Error)
        View.VISIBLE
    else
        View.GONE
    text = (responseState as UIResponseState.Error).errorMessage
}

@BindingAdapter("people")
fun RecyclerView.setPeople(responseState: UIResponseState) {
    if (isInstanceOf<UIResponseState.Success<List<Person>>>(responseState)) {
        val people = responseState as UIResponseState.Success<List<Person>>
        this.adapter = PeopleAdapter(context, people.content)
    } else {
        adapter = PeopleAdapter(context, listOf())
    }
}

inline fun <reified T> isInstanceOf(instance: Any?): Boolean {
    return instance is T
}
