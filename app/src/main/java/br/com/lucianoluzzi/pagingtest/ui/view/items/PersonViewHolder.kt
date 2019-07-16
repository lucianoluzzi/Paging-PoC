package br.com.lucianoluzzi.pagingtest.ui.view.items

import androidx.recyclerview.widget.RecyclerView
import br.com.lucianoluzzi.pagingtest.databinding.ItemPersonLayoutBinding
import br.com.lucianoluzzi.pagingtest.model.entity.Person

class PersonViewHolder(private val dataBinding: ItemPersonLayoutBinding) : RecyclerView.ViewHolder(dataBinding.root) {

    fun bind(person: Person) {
        dataBinding.person = person
    }
}