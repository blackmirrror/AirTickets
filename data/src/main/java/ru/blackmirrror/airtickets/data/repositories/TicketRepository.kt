package ru.blackmirrror.airtickets.data.repositories

import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import ru.blackmirrror.airtickets.data.models.EmptyData
import ru.blackmirrror.airtickets.data.models.NoConnection
import ru.blackmirrror.airtickets.data.models.ResultState
import ru.blackmirrror.airtickets.data.models.ServerError
import ru.blackmirrror.airtickets.data.models.Ticket
import ru.blackmirrror.airtickets.data.models.toTicket
import ru.blackmirrror.airtickets.data.models.toTicketDBO
import ru.blackmirrror.airtickets.data.room.AirTicketsDatabase
import ru.blackmirrror.airtickets.data.utils.ApiErrorHandler
import ru.blackmirrror.airtickets.data.utils.NetworkUtils
import ru.blackmirrror.aittickets.api.ApiService

class TicketRepository(
    private val context: Context,
    private val service: ApiService,
    private val database: AirTicketsDatabase,
    private val ioDispatcher: CoroutineDispatcher
) {

    fun getTickets(): Flow<ResultState<List<Ticket>>> {
        return ApiErrorHandler.handleErrors {
            flow {
                emit(ResultState.Loading())
                if (NetworkUtils.isInternetConnected(context)) {
                    val res = service.getTickets()
                    if (res.isSuccessful) {
                        val tickets = res.body()?.tickets
                        if (tickets != null) {
                            val mappedTickets = tickets.map { it.toTicket() }
                            emit(ResultState.Success(mappedTickets))
                            saveLocal(mappedTickets)
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

    private suspend fun saveLocal(tickets: List<Ticket>) {
        withContext(ioDispatcher) {
            database.ticketDao.clear()
            database.ticketDao.insert(tickets.map {
                it.toTicketDBO()
            })
        }
    }

    private suspend fun getLocal(): List<Ticket> {
        return database.ticketDao.getAll().map {
            it.toTicket()
        }
    }
}