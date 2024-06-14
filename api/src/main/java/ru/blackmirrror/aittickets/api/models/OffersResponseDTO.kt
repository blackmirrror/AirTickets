package ru.blackmirrror.aittickets.api.models

import com.google.gson.annotations.SerializedName

data class OffersResponseDTO (
    @SerializedName("offers" ) val offers : ArrayList<OfferDTO> = arrayListOf()
)

data class OfferDTO (
    @SerializedName("id"    ) val id    : Int?    = null,
    @SerializedName("title" ) val title : String? = null,
    @SerializedName("town"  ) val town  : String? = null,
    @SerializedName("price" ) val price : PriceDTO?  = PriceDTO()
)

data class PriceDTO (
    @SerializedName("value" ) val value : Int? = null
)
