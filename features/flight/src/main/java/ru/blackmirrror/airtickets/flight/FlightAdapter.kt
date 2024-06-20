package ru.blackmirrror.airtickets.flight

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.blackmirrror.airtickets.common.utils.TextFormatter
import ru.blackmirrror.airtickets.data.models.Flight
import ru.blackmirrror.airtickets.common.R as CommonR

class FlightAdapter :
    ListAdapter<Flight, FlightAdapter.FlightViewHolder>(FlightItemCallback()) {

    class FlightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val indicator = itemView.findViewById<View>(R.id.item_flight_indicator)
        val airline = itemView.findViewById<TextView>(R.id.item_flight_airline)
        val price = itemView.findViewById<TextView>(R.id.item_flight_price)
        val times = itemView.findViewById<TextView>(R.id.item_flight_times)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_flight, parent, false)
        return FlightViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlightViewHolder, position: Int) {
        val flight = getItem(position)
        with(holder) {
            airline.text = flight.title
            price.text = TextFormatter.priceToPatternString(flight.price)
            times.text = TextFormatter.timesToString(flight.timeRange)
            when (flight.id) {
                1 -> indicator.backgroundTintList =
                    ContextCompat.getColorStateList(indicator.context, CommonR.color.red)

                10 -> indicator.backgroundTintList =
                    ContextCompat.getColorStateList(indicator.context, CommonR.color.blue)

                30 -> indicator.backgroundTintList =
                    ContextCompat.getColorStateList(indicator.context, CommonR.color.white)
            }
        }
    }
}