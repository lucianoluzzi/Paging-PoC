package br.com.lucianoluzzi.pagingtest.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewModelScope
import br.com.lucianoluzzi.pagingtest.App
import br.com.lucianoluzzi.pagingtest.R
import br.com.lucianoluzzi.pagingtest.databinding.FragmentPeopleListBinding
import br.com.lucianoluzzi.pagingtest.ui.viewModel.PeopleViewModel
import kotlinx.coroutines.launch

class PeopleListFragment : Fragment() {
    private val viewModel: PeopleViewModel by lazy {
        ViewModelProviders.of(
            this,
            (activity?.application as App).component().peopleViewModel()
        ).get(PeopleViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentPeopleListBinding>(
            inflater,
            R.layout.fragment_people_list,
            container,
            false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchPeople()
    }

    private fun fetchPeople() {
        viewModel.viewModelScope.launch {
            viewModel.fetchPeople()
        }
    }
}