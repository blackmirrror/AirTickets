package ru.blackmirrror.aittickets.api.models

import com.google.gson.annotations.SerializedName

data class TicketsResponseDTO (
    @SerializedName("tickets" ) val tickets : ArrayList<TicketDTO> = arrayListOf()
)

data class TicketDTO (
    @SerializedName("id"                ) val id              : Int?         = null,
    @SerializedName("badge"             ) val badge           : String?      = null,
    @SerializedName("price"             ) val price           : PriceDTO?    = PriceDTO(),
    @SerializedName("provider_name"     ) val providerName    : String?      = null,
    @SerializedName("company"           ) val company         : String?      = null,
    @SerializedName("departure"         ) val departure       : DepartureDTO?   = DepartureDTO(),
    @SerializedName("arrival"           ) val arrival         : ArrivalDTO?     = ArrivalDTO(),
    @SerializedName("has_transfer"      ) val hasTransfer     : Boolean?     = null,
    @SerializedName("has_visa_transfer" ) val hasVisaTransfer : Boolean?     = null,
    @SerializedName("luggage"           ) val luggage         : LuggageDTO?     = LuggageDTO(),
    @SerializedName("hand_luggage"      ) val handLuggage     : HandLuggageDTO? = HandLuggageDTO(),
    @SerializedName("is_returnable"     ) val isReturnable    : Boolean?     = null,
    @SerializedName("is_exchangable"    ) val isExchangable   : Boolean?     = null
)

data class HandLuggageDTO (
    @SerializedName("has_hand_luggage" ) val hasHandLuggage : Boolean? = null,
    @SerializedName("size"             ) val size           : String?  = null
)

data class LuggageDTO (
    @SerializedName("has_luggage" ) val hasLuggage : Boolean? = null,
    @SerializedName("price"       ) val price      : PriceDTO?   = PriceDTO()
)

data class ArrivalDTO (
    @SerializedName("town"    ) val town    : String? = null,
    @SerializedName("date"    ) val date    : String? = null,
    @SerializedName("airport" ) val airport : String? = null
)

data class DepartureDTO (
    @SerializedName("town"    ) val town    : String? = null,
    @SerializedName("date"    ) val date    : String? = null,
    @SerializedName("airport" ) val airport : String? = null
)

