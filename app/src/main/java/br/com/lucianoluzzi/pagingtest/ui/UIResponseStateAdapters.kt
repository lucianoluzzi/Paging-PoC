package br.com.lucianoluzzi.pagingtest.ui

import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.lucianoluzzi.pagingtest.model.entity.Person
import br.com.lucianoluzzi.pagingtest.model.entity.UIResponseState
import br.com.lucianoluzzi.pagingtest.ui.view.items.PeopleAdapter

@BindingAdapter("hideOnLoading", requireAll = false)
fun ViewGroup.hideOnLoading(responseState: UIResponseState) {
    visibility = if(responseState is UIResponseState.Loading)
        View.GONE
    else
        View.VISIBLE
}

@BindingAdapter("people", requireAll = false)
fun RecyclerView.setPeople(responseState: UIResponseState) {
    if(responseState is UIResponseState.Success<*>) {
        val people = (responseState as UIResponseState.Success<List<Person>>)
        this.adapter = PeopleAdapter(context, people.content)
    } else {
        adapter = PeopleAdapter(context, listOf())
    }

    layoutManager = LinearLayoutManager(context)
}
