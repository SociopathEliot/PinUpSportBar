package com.pinup.barapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pinup.barapp.R
import com.pinup.barapp.domain.models.Event
import com.pinup.barapp.ui.adapters.EventAdapter


class EventFragment : Fragment(R.layout.fragment_event) {

    // обязательно уникальный id для каждого ивента!
    val events = listOf(
        Event(
            id = 1,
            title = "Happy Hour!",
            description = "50% discount on cocktails from 18:00 to 20:00. Hurry up to enjoy the best mixes of the evening!",
            imageRes = R.drawable.event_happy_hour
        ),
        Event(
            id = 2,
            title = "Burger & Pint",
            description = "Juicy burger + glass of beer for only €9.99! Every Tuesday.",
            imageRes = R.drawable.event_burger_pint
        ),
        Event(
            id = 3,
            title = "Match of the day: Bet on victory!",
            description = "Come watch the top match and get a free shot for every goal of your favorite team!",
            imageRes = R.drawable.event_match_day
        ),
        Event(
            id = 4,
            title = "Karaoke Night",
            description = "Every Friday from 22:00 – get discounts on drinks! The best singer of the evening gets a prize!",
            imageRes = R.drawable.event_karaoke_night
        ),
        Event(
            id = 5,
            title = "Whiskey and Poker Night",
            description = "Play American poker and get a 20% discount on the entire range of whiskey.",
            imageRes = R.drawable.event_whiskey_poker
        ),
        Event(
            id = 6,
            title = "Hen Party at Pin-up!",
            description = "A compliment to every girl – a free cocktail for a table of 20€ or more.",
            imageRes = R.drawable.event_hen_party
        ),
        Event(
            id = 7,
            title = "Birthday at Pin-up!",
            description = "Birthday boys and girls get a set of shots as a gift! Just open your passport and celebrate loudly!",
            imageRes = R.drawable.event_birthday
        )
    )

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
        adapter.submitList(events)
    }
}
