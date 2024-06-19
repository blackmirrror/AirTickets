package ru.blackmirrror.airtickets.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import ru.blackmirrror.airtickets.data.R
import ru.blackmirrror.airtickets.data.models.Place

class PlacesRepository(
    private val context: Context
) {
    @SuppressLint("UseCompatLoadingForDrawables")
    fun getPlaces(): List<Place> {
        return listOf(
            Place(
                1,
                context.getDrawable(R.drawable.place1),
                context.getString(R.string.place_one)
            ),
            Place(
                2,
                context.getDrawable(R.drawable.place2),
                context.getString(R.string.place_two)
            ),
            Place(
                3,
                context.getDrawable(R.drawable.place3),
                context.getString(R.string.place_three)
            )
        )
    }
}