package ru.blackmirrror.airtickets.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.blackmirrror.airtickets.data.models.Offer
import ru.blackmirrror.airtickets.data.models.ResultState
import ru.blackmirrror.airtickets.data.repositories.OfferRepository

class MainViewModel(
    private val offerRepository: OfferRepository
): ViewModel() {

    private val _offers = MutableStateFlow<ResultState<List<Offer>>>(ResultState.Loading())
    val offers: StateFlow<ResultState<List<Offer>>> = _offers
    init {
        loadOffers()
    }

    private fun loadOffers() {
        viewModelScope.launch {
            offerRepository.getOffers().collect {
                _offers.value = it
            }
        }
    }
}