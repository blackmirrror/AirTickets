package ru.blackmirrror.airtickets.main

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import ru.blackmirrror.airtickets.data.models.Offer

class OfferItemCallback: ItemCallback<Offer>() {
    override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean {
        return oldItem == newItem
    }
}