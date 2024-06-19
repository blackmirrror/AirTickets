package ru.blackmirrror.airtickets.data.repositories

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.blackmirrror.airtickets.data.models.EmptyData
import ru.blackmirrror.airtickets.data.models.NoConnection
import ru.blackmirrror.airtickets.data.models.ResultState
import ru.blackmirrror.airtickets.data.models.ServerError
import ru.blackmirrror.airtickets.data.models.Flight
import ru.blackmirrror.airtickets.data.models.toFlight
import ru.blackmirrror.airtickets.data.utils.ApiErrorHandler
import ru.blackmirrror.airtickets.data.utils.NetworkUtils
import ru.blackmirrror.aittickets.api.ApiService

class FlightRepository(
    private val context: Context,
    private val service: ApiService
) {
    fun getFlights(): Flow<ResultState<List<Flight>>> {
        return ApiErrorHandler.handleErrors {
            flow {
                emit(ResultState.Loading())
                if (NetworkUtils.isInternetConnected(context)) {
                    val res = service.getTicketsOffers()
                    if (res.isSuccessful) {
                        val offers = res.body()?.ticketsOffers
                        if (offers != null)
                            emit(ResultState.Success(offers.map { it.toFlight() }))
                        else {
                            emit(ResultState.Error(EmptyData))
                        }
                    } else
                        emit(ResultState.Error(ServerError))
                } else {
                    emit(ResultState.Error(NoConnection))
                }
            }
        }
    }
}