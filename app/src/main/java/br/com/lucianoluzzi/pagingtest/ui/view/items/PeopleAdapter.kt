package br.com.lucianoluzzi.pagingtest.ui.view.items

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.lucianoluzzi.pagingtest.R
import br.com.lucianoluzzi.pagingtest.databinding.ItemPersonLayoutBinding
import br.com.lucianoluzzi.pagingtest.model.entity.Person

class PeopleAdapter(
    private val context: Context,
    private val people: List<Person>
) : RecyclerView.Adapter<PersonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val layoutBinding =
            DataBindingUtil.inflate<ItemPersonLayoutBinding>(layoutInflater, R.layout.item_person_layout, parent, false)
        return PersonViewHolder(layoutBinding)
    }

    override fun getItemCount(): Int {
        return people.size
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(people[position])
    }
}