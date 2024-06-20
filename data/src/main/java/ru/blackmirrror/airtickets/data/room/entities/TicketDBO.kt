package ru.blackmirrror.airtickets.data.room.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "tickets")
data class TicketDBO(
    @PrimaryKey val id: Int?,

    val badge: String? = null,
    val price: Int? = null,
    val providerName: String? = null,
    val company: String? = null,

    @Embedded(prefix = "departure_")
    val departure: DepartureDBO? = DepartureDBO(),

    @Embedded(prefix = "arrival_")
    val arrival: DepartureDBO? = DepartureDBO(),

    val hasTransfer: Boolean? = null,
    val hasVisaTransfer: Boolean? = null,

    @Embedded(prefix = "luggage_")
    val luggage: LuggageDBO? = LuggageDBO(),

    @Embedded(prefix = "hand_luggage_")
    val handLuggage: HandLuggageDBO? = HandLuggageDBO(),

    val isReturnable: Boolean? = null,
    val isExchangable: Boolean? = null
)

data class HandLuggageDBO(
    val hasHandLuggage: Boolean? = null,
    val size: String? = null
)

data class LuggageDBO(
    val hasLuggage: Boolean? = null,
    val price: Int? = null
)

data class DepartureDBO(
    val town: String? = null,
    val date: Date? = null,
    val airport: String? = null
)
