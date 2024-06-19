package ru.blackmirrror.airtickets.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.blackmirrror.airtickets.flight.FlightViewModel
import ru.blackmirrror.airtickets.main.MainViewModel
import ru.blackmirrror.airtickets.search.PlacesViewModel
import ru.blackmirrror.airtickets.ticket.TicketViewModel

val appModule = module {

    viewModel {
        MainViewModel(
            offerRepository = get(),
            searchRepository = get()
        )
    }

    viewModel {
        FlightViewModel(
            flightRepository = get()
        )
    }

    viewModel {
        TicketViewModel(
            ticketRepository = get()
        )
    }

    viewModel {
        PlacesViewModel(
            placesRepository = get()
        )
    }
}
