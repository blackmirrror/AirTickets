package ru.blackmirrror.airtickets.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flights")
data class FlightDBO(
    @PrimaryKey val id: Int?,
    val title: String? = null,
    val timeRange: String? = null,
    val price: Int? = null
)
