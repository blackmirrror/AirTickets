package ru.blackmirrror.airtickets.ticket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.blackmirrror.airtickets.common.utils.TextFormatter
import ru.blackmirrror.airtickets.data.models.Ticket

class TicketAdapter : ListAdapter<Ticket, TicketAdapter.TicketViewHolder>(TicketItemCallback()) {

    class TicketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val price = itemView.findViewById<TextView>(R.id.item_ticket_price)
        val timeFrom = itemView.findViewById<TextView>(R.id.item_ticket_time_from)
        val timeTo = itemView.findViewById<TextView>(R.id.item_ticket_time_to)
        val airportsFrom = itemView.findViewById<TextView>(R.id.item_ticket_airports_from)
        val airportsTo = itemView.findViewById<TextView>(R.id.item_ticket_airports_to)
        val timeIn = itemView.findViewById<TextView>(R.id.item_ticket_time_in)
        val hasTransfer = itemView.findViewById<TextView>(R.id.item_ticket_has_transfer)
        val bestOption = itemView.findViewById<TextView>(R.id.item_ticket_best_option)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ticket, parent, false)
        return TicketViewHolder(view)
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        val ticket = getItem(position)
        with(holder) {
            price.text = TextFormatter.priceToPatternString(ticket.price)
            timeFrom.text = TextFormatter.dateConvertToTime(ticket.departure?.date)
            airportsFrom.text = ticket.departure?.airport
            timeTo.text = TextFormatter.dateConvertToTime(ticket.arrival?.date)
            airportsTo.text = ticket.arrival?.airport
            timeIn.text =
                TextFormatter.hoursBetweenDates(ticket.departure?.date, ticket.arrival?.date)

            if (ticket.hasTransfer != null) {
                if (ticket.hasTransfer == true)
                    hasTransfer.visibility = View.GONE
                else
                    hasTransfer.visibility = View.VISIBLE

            }

            if (ticket.badge != null) {
                bestOption.visibility = View.VISIBLE
                bestOption.text = ticket.badge
            } else
                bestOption.visibility = View.GONE
        }
    }
}