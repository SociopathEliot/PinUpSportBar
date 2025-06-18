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
import com.pinup.barapp.domain.models.Match

class ScheduleAdapter : ListAdapter<Match, ScheduleAdapter.MatchViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_schedule, parent, false)
        return MatchViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val homeName = view.findViewById<TextView>(R.id.tvHomeName)
        private val awayName = view.findViewById<TextView>(R.id.tvAwayName)
        private val league = view.findViewById<TextView>(R.id.tvLeague)
        private val time = view.findViewById<TextView>(R.id.tvTime)
        private val status = view.findViewById<TextView>(R.id.tvStatus)
        private val homeLogo = view.findViewById<ImageView>(R.id.ivHomeLogo)
        private val awayLogo = view.findViewById<ImageView>(R.id.ivAwayLogo)

        fun bind(match: Match) {
            homeName.text = match.homeName
            awayName.text = match.awayName
            league.text = match.league
            time.text = match.time
            status.text = match.status
            // For simplicity we skip image loading libraries
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Match>() {
        override fun areItemsTheSame(oldItem: Match, newItem: Match): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Match, newItem: Match): Boolean = oldItem == newItem
    }
}
