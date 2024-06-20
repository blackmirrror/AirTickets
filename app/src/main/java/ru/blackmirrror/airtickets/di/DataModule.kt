package ru.blackmirrror.airtickets.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.blackmirrror.airtickets.BuildConfig
import ru.blackmirrror.airtickets.data.repositories.FlightRepository
import ru.blackmirrror.airtickets.data.repositories.OfferRepository
import ru.blackmirrror.airtickets.data.repositories.PlacesRepository
import ru.blackmirrror.airtickets.data.repositories.SearchRepository
import ru.blackmirrror.airtickets.data.repositories.TicketRepository
import ru.blackmirrror.airtickets.data.room.AirTicketsDatabase
import ru.blackmirrror.airtickets.data.sharedPrefs.LastSearchSharedPreferences
import ru.blackmirrror.aittickets.api.ApiFactory
import ru.blackmirrror.aittickets.api.ApiService

val dataModule = module {

    single<ApiService> {
        ApiFactory.create(BuildConfig.BASE_URL)
    }

    single<AirTicketsDatabase> {
        AirTicketsDatabase(
            context = get()
        )
    }

    single<OfferRepository> {
        OfferRepository(
            context = get(),
            service = get(),
            database = get(),
            ioDispatcher = get(named("IODispatcher"))
        )
    }

    single<FlightRepository> {
        FlightRepository(
            context = get(),
            service = get(),
            database = get(),
            ioDispatcher = get(named("IODispatcher"))
        )
    }

    single<TicketRepository> {
        TicketRepository(
            context = get(),
            service = get(),
            database = get(),
            ioDispatcher = get(named("IODispatcher"))
        )
    }

    single<PlacesRepository> {
        PlacesRepository(
            context = get()
        )
    }
    single<LastSearchSharedPreferences> {
        LastSearchSharedPreferences(
            context = get()
        )
    }

    single<SearchRepository> {
        SearchRepository(
            lastSearchSharedPreferences = get()
        )
    }
}
