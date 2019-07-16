package br.com.lucianoluzzi.pagingtest.ui.view.items

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.lucianoluzzi.pagingtest.model.entity.Person

@BindingAdapter("people", requireAll = false)
fun RecyclerView.setArticles(people: List<Person>?) {
    this.adapter = people?.let {
        PeopleAdapter(context, people)
    } ?: run {
        PeopleAdapter(context, listOf())
    }
    layoutManager = LinearLayoutManager(context)
}
