package ru.blackmirrror.airtickets.data.models

import ru.blackmirrror.airtickets.data.room.entities.DepartureDBO
import ru.blackmirrror.airtickets.data.room.entities.FlightDBO
import ru.blackmirrror.airtickets.data.room.entities.HandLuggageDBO
import ru.blackmirrror.airtickets.data.room.entities.LuggageDBO
import ru.blackmirrror.airtickets.data.room.entities.OfferDBO
import ru.blackmirrror.airtickets.data.room.entities.TicketDBO
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

internal fun Offer.toOfferDBO(imagePath: String?): OfferDBO {
    return OfferDBO(
        id = id,
        title = title,
        town = town,
        price = price,
        imagePath = imagePath
    )
}

internal fun OfferDBO.toOffer(): Offer {
    return Offer(
        id = id,
        title = title,
        town = town,
        price = price
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

internal fun Flight.toFlightDBO(): FlightDBO {
    return FlightDBO(
        id = id,
        title = title,
        timeRange = convertListToString(timeRange),
        price = price
    )
}

internal fun FlightDBO.toFlight(): Flight {
    return Flight(
        id = id,
        title = title,
        timeRange = convertStringToList(timeRange),
        price = price
    )
}

private fun convertListToString(list: List<String>): String {
    return list.joinToString(",")
}

private fun convertStringToList(string: String?): List<String> {
    if (string != null) {
        return string.split(",")
    }
    return emptyList()
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

internal fun Ticket.toTicketDBO(): TicketDBO {
    return TicketDBO(
        id = id,
        badge = badge,
        price = price,
        providerName = providerName,
        company = company,
        departure = departure?.toDepartureDBO(),
        arrival = arrival?.toDepartureDBO(),
        hasTransfer = hasTransfer,
        hasVisaTransfer = hasVisaTransfer,
        luggage = luggage?.toLuggageDBO(),
        handLuggage = handLuggage?.toHandLuggageDBO(),
        isReturnable = isReturnable,
        isExchangable = isExchangable
    )
}

private fun HandLuggage.toHandLuggageDBO(): HandLuggageDBO {
    return HandLuggageDBO(
        hasHandLuggage = hasHandLuggage,
        size = size
    )
}

private fun Luggage.toLuggageDBO(): LuggageDBO {
    return LuggageDBO(
        hasLuggage = hasLuggage,
        price = price
    )
}

private fun Departure.toDepartureDBO(): DepartureDBO {
    return DepartureDBO(
        town = town,
        date = date,
        airport = airport
    )
}

internal fun TicketDBO.toTicket(): Ticket {
    return Ticket(
        id = id,
        badge = badge,
        price = price,
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

private fun HandLuggageDBO.toHandLuggage(): HandLuggage {
    return HandLuggage(
        hasHandLuggage = hasHandLuggage,
        size = size
    )
}

private fun LuggageDBO.toLuggage(): Luggage {
    return Luggage(
        hasLuggage = hasLuggage,
        price = price
    )
}

private fun DepartureDBO.toDeparture(): Departure {
    return Departure(
        town = town,
        date = date,
        airport = airport
    )
}