package ru.blackmirrror.airtickets.data.repositories

import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.blackmirrror.airtickets.data.models.EmptyData
import ru.blackmirrror.airtickets.data.models.NoConnection
import ru.blackmirrror.airtickets.data.models.ResultState
import ru.blackmirrror.airtickets.data.models.ServerError
import ru.blackmirrror.airtickets.data.models.Flight
import ru.blackmirrror.airtickets.data.models.toFlight
import ru.blackmirrror.airtickets.data.models.toFlightDBO
import ru.blackmirrror.airtickets.data.room.AirTicketsDatabase
import ru.blackmirrror.airtickets.data.utils.ApiErrorHandler
import ru.blackmirrror.airtickets.data.utils.NetworkUtils
import ru.blackmirrror.aittickets.api.ApiService

class FlightRepository(
    private val context: Context,
    private val service: ApiService,
    private val database: AirTicketsDatabase,
    private val ioDispatcher: CoroutineDispatcher
) {

    fun getFlights(): Flow<ResultState<List<Flight>>> {
        return ApiErrorHandler.handleErrors {
            flow {
                emit(ResultState.Loading())
                if (NetworkUtils.isInternetConnected(context)) {
                    val res = service.getTicketsOffers()
                    if (res.isSuccessful) {
                        val flights = res.body()?.ticketsOffers
                        if (flights != null) {
                            val mappedFlights = flights.map { it.toFlight() }
                            emit(ResultState.Success(mappedFlights))
                            saveLocal(mappedFlights)
                        }
                        else {
                            emit(ResultState.Error(EmptyData, getLocal()))
                        }
                    } else
                        emit(ResultState.Error(ServerError, getLocal()))
                } else {
                    emit(ResultState.Error(NoConnection, getLocal()))
                }
            }
        }
    }

    private suspend fun saveLocal(flights: List<Flight>) {
        withContext(ioDispatcher) {
            database.flightDao.clear()
            database.flightDao.insert(flights.map {
                it.toFlightDBO()
            })
        }
    }

    private suspend fun getLocal(): List<Flight> {
        return database.flightDao.getAll().map {
            it.toFlight()
        }
    }
}