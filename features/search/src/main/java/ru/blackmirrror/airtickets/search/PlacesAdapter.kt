package ru.blackmirrror.airtickets.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class PlacesAdapter: ListAdapter<ru.blackmirrror.airtickets.data.models.Place, PlacesAdapter.PlacesViewHolder>(PlacesItemCallback()) {

    var onPlaceItemClickListener: ((ru.blackmirrror.airtickets.data.models.Place) -> Unit)? = null

    class PlacesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val town = itemView.findViewById<TextView>(R.id.item_search_place_town)
        val image = itemView.findViewById<ImageView>(R.id.item_search_place_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_place, parent, false)
        return PlacesViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) {
        val place = getItem(position)
        with(holder) {
            town.text = place.town
            image.setImageDrawable(place.image)
            itemView.setOnClickListener {
                onPlaceItemClickListener?.invoke(place)
            }
        }
    }
}