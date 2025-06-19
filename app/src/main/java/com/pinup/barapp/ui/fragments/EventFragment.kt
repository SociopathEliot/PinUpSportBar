package com.pinup.barapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pinup.barapp.R
import com.pinup.barapp.data.repositories.EventRepository
import com.pinup.barapp.ui.adapters.EventAdapter


class EventFragment : Fragment(R.layout.fragment_event) {


    private lateinit var adapter: EventAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler = view.findViewById<RecyclerView>(R.id.recyclerEvents)

        // Передаём функцию, что делать при нажатии на карточку события
        adapter = EventAdapter { event ->
            findNavController().navigate(EventFragmentDirections.actionEventFragmentToEventFragmentDetail(event.id))
        }

        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
        adapter.submitList(EventRepository.events)
    }
}
