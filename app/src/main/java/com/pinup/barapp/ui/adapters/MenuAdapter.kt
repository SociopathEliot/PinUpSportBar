package com.pinup.barapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pinup.barapp.R
import com.pinup.barapp.domain.models.MenuItem

class MenuAdapter(
    private val onOrderClick: (MenuItem) -> Unit
) : ListAdapter<MenuItem, MenuAdapter.MenuViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_menu, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MenuViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val image = view.findViewById<ImageView>(R.id.item_image)
        private val title = view.findViewById<TextView>(R.id.item_title)
        private val desc = view.findViewById<TextView>(R.id.item_description)
        private val price = view.findViewById<TextView>(R.id.item_price)
        private val orderBtn = view.findViewById<Button>(R.id.item_order_btn)

        fun bind(item: MenuItem) {
            title.text = item.name
            desc.text = item.description
            price.text = "$ %.2f".format(item.price)
            image.setImageResource(item.imageRes)
            orderBtn.setOnClickListener { onOrderClick(item) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<MenuItem>() {
        override fun areItemsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean =
            oldItem == newItem
    }
}
