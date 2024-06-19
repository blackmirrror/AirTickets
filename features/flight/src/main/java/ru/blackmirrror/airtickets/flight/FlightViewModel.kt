package ru.blackmirrror.airtickets.flight

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.blackmirrror.airtickets.data.models.ResultState
import ru.blackmirrror.airtickets.data.models.Flight
import ru.blackmirrror.airtickets.data.repositories.FlightRepository
import java.util.Date

class FlightViewModel(
    private val flightRepository: FlightRepository
): ViewModel() {

    private val _flights = MutableStateFlow<ResultState<List<Flight>>>(ResultState.Loading())
    val flights: StateFlow<ResultState<List<Flight>>> = _flights

    private val _departureDate = MutableStateFlow<Date?>(null)
    val departureDate: StateFlow<Date?> = _departureDate

    private val _arrivalDate = MutableStateFlow<Date?>(null)
    val arrivalDate: StateFlow<Date?> = _arrivalDate

    init {
        loadOffers()
    }

    private fun loadOffers() {
        viewModelScope.launch {
            flightRepository.getFlights().collect {
                _flights.value = it
            }
        }
    }

    fun setDepartureDate(date: Date) {
        _departureDate.value = date
    }

    fun setArrivalDate(date: Date) {
        _arrivalDate.value = date
    }
}