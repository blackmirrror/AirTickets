package ru.blackmirrror.aittickets.api.models

import com.google.gson.annotations.SerializedName

data class TicketsOffersResponseDTO (
    @SerializedName("tickets_offers" ) val ticketsOffers : ArrayList<TicketsOfferDTO> = arrayListOf()
)

data class TicketsOfferDTO (
    @SerializedName("id"         ) val id        : Int?              = null,
    @SerializedName("title"      ) val title     : String?           = null,
    @SerializedName("time_range" ) val timeRange : ArrayList<String> = arrayListOf(),
    @SerializedName("price"      ) val price     : PriceDTO?         = PriceDTO()
)
