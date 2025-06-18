package com.pinup.barapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.R
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
            tvTeamA.text = match.homeName
            tvTeamB.text = match.awayName
            tvLeague.text = match.league
            tvDateTime.text = match.time
            tvStatus.text = match.status
            Glide
                .with(imgTeamA)
                .load(match.homeLogo)
                .centerCrop()
                .into(imgTeamA);

            Glide
                .with(imgTeamB)
                .load(match.awayLogo)
                .centerCrop()
                .into(imgTeamB);

        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Match>() {
        override fun areItemsTheSame(oldItem: Match, newItem: Match): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Match, newItem: Match): Boolean = oldItem == newItem
    }
}
