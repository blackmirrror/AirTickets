package ru.blackmirrror.airtickets.data.repositories

import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import ru.blackmirrror.airtickets.data.R
import ru.blackmirrror.airtickets.data.models.EmptyData
import ru.blackmirrror.airtickets.data.models.NoConnection
import ru.blackmirrror.airtickets.data.models.Offer
import ru.blackmirrror.airtickets.data.models.ResultState
import ru.blackmirrror.airtickets.data.models.ServerError
import ru.blackmirrror.airtickets.data.models.toOffer
import ru.blackmirrror.airtickets.data.models.toOfferDBO
import ru.blackmirrror.airtickets.data.room.AirTicketsDatabase
import ru.blackmirrror.airtickets.data.room.utils.ImageFileManager.deleteFolder
import ru.blackmirrror.airtickets.data.room.utils.ImageFileManager.loadImageFromStorage
import ru.blackmirrror.airtickets.data.room.utils.ImageFileManager.saveImageToStorage
import ru.blackmirrror.airtickets.data.utils.ApiErrorHandler.handleErrors
import ru.blackmirrror.airtickets.data.utils.NetworkUtils
import ru.blackmirrror.aittickets.api.ApiService

class OfferRepository(
    private val context: Context,
    private val service: ApiService,
    private val database: AirTicketsDatabase,
    private val ioDispatcher: CoroutineDispatcher
) {

    fun getOffers(): Flow<ResultState<List<Offer>>> {
        return handleErrors {
            flow {
                emit(ResultState.Loading())
                if (NetworkUtils.isInternetConnected(context)) {
                    val res = service.getOffers()
                    if (res.isSuccessful) {
                        val offers = res.body()?.offers
                        if (offers != null) {
                            val mappedOffers = offers.map {
                                it.toOffer().apply {
                                    when (this.id) {
                                        1 -> this.image = context.getDrawable(R.drawable.music1)
                                        2 -> this.image = context.getDrawable(R.drawable.music2)
                                        else -> this.image = context.getDrawable(R.drawable.music3)
                                    }
                                }
                            }
                            emit(ResultState.Success(mappedOffers))
                            saveLocal(mappedOffers)
                        } else {
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

    private suspend fun saveLocal(offers: List<Offer>) {
        withContext(ioDispatcher) {
            database.offerDao.clear()
            deleteFolder(context, context.getString(R.string.folder_cache_offers), ioDispatcher)
            database.offerDao.insert(offers.map {
                it.toOfferDBO(
                    saveImageToStorage(
                        context,
                        it.image,
                        context.getString(R.string.folder_cache_offers),
                        ioDispatcher
                    )
                )
            })
        }
    }

    private suspend fun getLocal(): List<Offer> {
        return database.offerDao.getAll().map {
            val imagePath = it.imagePath
            val offer = it.toOffer()
            offer.image = loadImageFromStorage(context, imagePath, ioDispatcher)
            offer
        }
    }
}