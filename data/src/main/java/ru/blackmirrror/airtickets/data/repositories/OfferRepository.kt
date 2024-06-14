package ru.blackmirrror.airtickets.data.repositories

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.blackmirrror.airtickets.data.R
import ru.blackmirrror.airtickets.data.models.EmptyData
import ru.blackmirrror.airtickets.data.models.NoConnection
import ru.blackmirrror.airtickets.data.models.Offer
import ru.blackmirrror.airtickets.data.models.ResultState
import ru.blackmirrror.airtickets.data.models.ServerError
import ru.blackmirrror.airtickets.data.models.toOffer
import ru.blackmirrror.airtickets.data.utils.ApiErrorHandler.handleErrors
import ru.blackmirrror.airtickets.data.utils.NetworkUtils
import ru.blackmirrror.aittickets.api.ApiService

class OfferRepository(
    private val context: Context,
    private val service: ApiService
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
                        }
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