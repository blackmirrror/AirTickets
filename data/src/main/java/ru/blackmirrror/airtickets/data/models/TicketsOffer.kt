package ru.blackmirrror.airtickets.data.models

data class TicketsOffer (
    val id        : Int?              = null,
    val title     : String?           = null,
    val timeRange : ArrayList<String> = arrayListOf(),
    val price     : Int?              = null
)
