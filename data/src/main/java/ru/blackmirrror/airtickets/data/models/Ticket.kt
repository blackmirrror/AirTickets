package ru.blackmirrror.airtickets.data.models

data class Ticket (
    val id              : Int?         = null,
    val badge           : String?      = null,
    val price           : Int?         = null,
    val providerName    : String?      = null,
    val company         : String?      = null,
    val departure       : Departure?   = Departure(),
    val arrival         : Arrival?     = Arrival(),
    val hasTransfer     : Boolean?     = null,
    val hasVisaTransfer : Boolean?     = null,
    val luggage         : Luggage?     = Luggage(),
    val handLuggage     : HandLuggage? = HandLuggage(),
    val isReturnable    : Boolean?     = null,
    val isExchangable   : Boolean?     = null
)

data class HandLuggage (
    val hasHandLuggage : Boolean? = null,
    val size           : String?  = null
)

data class Luggage (
    val hasLuggage : Boolean? = null,
    val price      : Int?     = null
)

data class Arrival (
    val town    : String? = null,
    val date    : String? = null,
    val airport : String? = null
)

data class Departure (
    val town    : String? = null,
    val date    : String? = null,
    val airport : String? = null
)