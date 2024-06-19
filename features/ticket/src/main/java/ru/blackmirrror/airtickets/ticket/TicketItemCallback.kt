package ru.blackmirrror.airtickets.ticket

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import ru.blackmirrror.airtickets.data.models.Ticket

class TicketItemCallback: ItemCallback<Ticket>() {
    override fun areItemsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
        return oldItem == newItem
    }
}