package ru.blackmirrror.airtickets.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "offers")
data class OfferDBO(
    @PrimaryKey val id: Int?,
    val title: String?,
    val town: String?,
    val price: Int?,
    val imagePath: String?
)