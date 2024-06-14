package ru.blackmirrror.airtickets.di

import org.koin.dsl.module
import ru.blackmirrror.airtickets.data.repositories.OfferRepository
import ru.blackmirrror.airtickets.data.repositories.TicketRepository
import ru.blackmirrror.airtickets.data.repositories.TicketsOfferRepository
import ru.blackmirrror.aittickets.api.ApiFactory
import ru.blackmirrror.aittickets.api.ApiService

val dataModule = module {

    single<ApiService> {
        ApiFactory.create()
    }

    single<OfferRepository> {
        OfferRepository(
            context = get(),
            service = get()
        )
    }

    single<TicketsOfferRepository> {
        TicketsOfferRepository(
            context = get(),
            service = get()
        )
    }

    single<TicketRepository> {
        TicketRepository(
            context = get(),
            service = get()
        )
    }
}