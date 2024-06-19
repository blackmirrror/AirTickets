package ru.blackmirrror.airtickets.search

import androidx.recyclerview.widget.DiffUtil.ItemCallback

class PlacesItemCallback: ItemCallback<ru.blackmirrror.airtickets.data.models.Place>() {
    override fun areItemsTheSame(oldItem: ru.blackmirrror.airtickets.data.models.Place, newItem: ru.blackmirrror.airtickets.data.models.Place): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ru.blackmirrror.airtickets.data.models.Place, newItem: ru.blackmirrror.airtickets.data.models.Place): Boolean {
        return oldItem == newItem
    }
}