package ru.blackmirrror.airtickets.search

import androidx.recyclerview.widget.DiffUtil.ItemCallback

class PlacesItemCallback: ItemCallback<Place>() {
    override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
        return oldItem == newItem
    }
}