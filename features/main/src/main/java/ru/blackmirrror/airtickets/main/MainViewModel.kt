package ru.blackmirrror.airtickets.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.blackmirrror.airtickets.common.NavigationHandler
import ru.blackmirrror.airtickets.data.models.Offer
import ru.blackmirrror.airtickets.data.models.ResultState
import ru.blackmirrror.airtickets.data.repositories.OfferRepository
import ru.blackmirrror.airtickets.data.repositories.SearchRepository

class MainViewModel(
    private val offerRepository: OfferRepository,
    private val searchRepository: SearchRepository
): ViewModel() {

    private val _offers = MutableStateFlow<ResultState<List<Offer>>>(ResultState.Loading())
    val offers: StateFlow<ResultState<List<Offer>>> = _offers

    var hasErrorShown = false

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

    fun getLastSearch(): String? {
        return searchRepository.getLastSearch()
    }

    fun saveLastSearch(word: String) {
        searchRepository.saveLastSearch(word)
    }
}