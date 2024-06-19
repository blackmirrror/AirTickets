package ru.blackmirrror.airtickets.search

import androidx.lifecycle.ViewModel
import ru.blackmirrror.airtickets.data.models.Place
import ru.blackmirrror.airtickets.data.repositories.PlacesRepository

class PlacesViewModel(
    placesRepository: PlacesRepository
): ViewModel() {
    val places: List<Place> = placesRepository.getPlaces()
}