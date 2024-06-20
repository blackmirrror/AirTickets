package ru.blackmirrror.aittickets.api

import retrofit2.Response
import retrofit2.http.GET
import ru.blackmirrror.aittickets.api.models.OffersResponseDTO
import ru.blackmirrror.aittickets.api.models.TicketsOffersResponseDTO
import ru.blackmirrror.aittickets.api.models.TicketsResponseDTO

interface ApiService {

    @GET("v3/ad9a46ba-276c-4a81-88a6-c068e51cce3a")
    suspend fun getOffers(): Response<OffersResponseDTO>

    @GET("v3/38b5205d-1a3d-4c2f-9d77-2f9d1ef01a4a")
    suspend fun getTicketsOffers(): Response<TicketsOffersResponseDTO>

    @GET("v3/c0464573-5a13-45c9-89f8-717436748b69")
    suspend fun getTickets(): Response<TicketsResponseDTO>
}