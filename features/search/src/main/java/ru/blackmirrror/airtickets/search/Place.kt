package ru.blackmirrror.airtickets.search

import android.graphics.drawable.Drawable

data class Place(
    val id: Int,
    val image: Drawable?,
    val town: String
)
