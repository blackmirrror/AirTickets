package ru.blackmirrror.airtickets.data.models

import android.graphics.drawable.Drawable

data class Offer(
    val id    : Int?    = null,
    val title : String? = null,
    val town  : String? = null,
    val price : Int?    = null,
    var image: Drawable? = null
)
