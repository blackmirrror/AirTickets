package ru.blackmirrror.airtickets.data.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import ru.blackmirrror.airtickets.data.models.ResultState
import ru.blackmirrror.airtickets.data.models.ServerError


object ApiErrorHandler {
    fun <T> handleErrors(action: suspend () -> Flow<T>): Flow<T> {
        return flow {
            try {
                emitAll(action())
            } catch (e: Exception) {
                emit(ResultState.Error<T>(ServerError) as T)
            }
        }
    }
}