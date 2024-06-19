package ru.blackmirrror.airtickets.data.models

import java.util.Date

data class Ticket (
    val id              : Int?         = null,
    val badge           : String?      = null,
    val price           : Int?         = null,
    val providerName    : String?      = null,
    val company         : String?      = null,
    val departure       : Departure?   = Departure(),
    val arrival         : Departure?     = Departure(),
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

data class Departure (
    val town    : String? = null,
    val date    : Date? = null,
    val airport : String? = null
)