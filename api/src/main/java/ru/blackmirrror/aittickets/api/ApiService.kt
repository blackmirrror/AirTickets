package ru.blackmirrror.aittickets.api

import retrofit2.Response
import retrofit2.http.GET
import ru.blackmirrror.aittickets.api.models.OffersResponseDTO
import ru.blackmirrror.aittickets.api.models.TicketsOffersResponseDTO
import ru.blackmirrror.aittickets.api.models.TicketsResponseDTO

interface ApiService {

    @GET("v3/214a1713-bac0-4853-907c-a1dfc3cd05fd")
    suspend fun getOffers(): Response<OffersResponseDTO>

    @GET("v3/7e55bf02-89ff-4847-9eb7-7d83ef884017")
    suspend fun getTicketsOffers(): Response<TicketsOffersResponseDTO>

    @GET("v3/670c3d56-7f03-4237-9e34-d437a9e56ebf")
    suspend fun getTickets(): Response<TicketsResponseDTO>
}