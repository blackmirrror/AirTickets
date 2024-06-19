package ru.blackmirrror.airtickets.data.models

import ru.blackmirrror.aittickets.api.models.DepartureDTO
import ru.blackmirrror.aittickets.api.models.HandLuggageDTO
import ru.blackmirrror.aittickets.api.models.LuggageDTO
import ru.blackmirrror.aittickets.api.models.OfferDTO
import ru.blackmirrror.aittickets.api.models.TicketDTO
import ru.blackmirrror.aittickets.api.models.TicketsOfferDTO

internal fun OfferDTO.toOffer(): Offer {
    return Offer(
        id = id,
        title = title,
        town = town,
        price = price?.value
    )
}

internal fun TicketsOfferDTO.toFlight(): Flight {
    return Flight(
        id = id,
        title = title,
        timeRange = timeRange,
        price = price?.value
    )
}

internal fun TicketDTO.toTicket(): Ticket {
    return Ticket(
        id = id,
        badge = badge,
        price = price?.value,
        providerName = providerName,
        company = company,
        departure = departure?.toDeparture(),
        arrival = arrival?.toDeparture(),
        hasTransfer = hasTransfer,
        hasVisaTransfer = hasVisaTransfer,
        luggage = luggage?.toLuggage(),
        handLuggage = handLuggage?.toHandLuggage(),
        isReturnable = isReturnable,
        isExchangable = isExchangable
    )
}

private fun HandLuggageDTO.toHandLuggage(): HandLuggage {
    return HandLuggage(
        hasHandLuggage = hasHandLuggage,
        size = size
    )
}

private fun LuggageDTO.toLuggage(): Luggage {
    return Luggage(
        hasLuggage = hasLuggage,
        price = price?.value
    )
}

private fun DepartureDTO.toDeparture(): Departure {
    return Departure(
        town = town,
        date = date,
        airport = airport
    )
}