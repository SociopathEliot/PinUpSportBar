package com.pinup.barapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pinup.barapp.R
import com.pinup.barapp.databinding.ItemScheduleBinding
import com.pinup.barapp.domain.models.Match

class ScheduleAdapter : ListAdapter<Match, ScheduleAdapter.MatchViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val binding = ItemScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MatchViewHolder(private val binding: ItemScheduleBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(match: Match) = with(binding) {
            tvHomeName.text = match.homeName
            tvAwayName.text = match.awayName
            tvLeague.text = match.league
            tvTime.text = match.time
            tvStatus.text = match.status
            // TODO: load team logos into ivHomeLogo and ivAwayLogo
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Match>() {
        override fun areItemsTheSame(oldItem: Match, newItem: Match): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Match, newItem: Match): Boolean = oldItem == newItem
    }
}
