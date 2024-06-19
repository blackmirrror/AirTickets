package ru.blackmirrror.airtickets.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.blackmirrror.airtickets.common.utils.TextFormatter
import ru.blackmirrror.airtickets.data.models.Offer

class OfferAdapter: ListAdapter<Offer, OfferAdapter.OfferViewHolder>(OfferItemCallback()) {

    class OfferViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.item_music_image)
        val name = itemView.findViewById<TextView>(R.id.item_music_name)
        val town = itemView.findViewById<TextView>(R.id.item_music_town)
        val price = itemView.findViewById<TextView>(R.id.item_music_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_music, parent, false)
        return OfferViewHolder(view)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val offer = getItem(position)
        with(holder) {
            image.setImageDrawable(offer.image)
            name.text = offer.title
            town.text = offer.town
            price.text = TextFormatter.priceToPatternString(offer.price)
        }
    }
}