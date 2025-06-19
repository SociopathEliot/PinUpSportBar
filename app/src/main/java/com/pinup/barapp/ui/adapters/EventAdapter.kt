package com.pinup.barapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pinup.barapp.R
import com.pinup.barapp.domain.models.Event

class EventAdapter(
    private val onClick: (Event) -> Unit
) : ListAdapter<Event, EventAdapter.EventViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val image = view.findViewById<ImageView>(R.id.ivEvent)
        private val title = view.findViewById<TextView>(R.id.tvEventTitle)
        private val desc = view.findViewById<TextView>(R.id.tvEventDesc)

        fun bind(item: Event) {
            title.text = item.title
            desc.text = item.description
            image.setImageResource(item.imageRes)
            itemView.setOnClickListener { onClick(item) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean =
            oldItem == newItem
    }
}
