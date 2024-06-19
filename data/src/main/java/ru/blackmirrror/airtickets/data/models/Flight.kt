package ru.blackmirrror.airtickets.data.models

data class Flight (
    val id        : Int?              = null,
    val title     : String?           = null,
    val timeRange : List<String>      = listOf(),
    val price     : Int?              = null
)
