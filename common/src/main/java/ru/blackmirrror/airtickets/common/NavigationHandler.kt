package ru.blackmirrror.airtickets.common

import java.util.Date

interface NavigationHandler {
    fun actionMainFragmentToSearchFragment(from: String)
    fun actionSearchFragmentToPlugSearchFragment()
    fun actionSearchFragmentToFlightFragment(from: String, to: String)
    fun actionFlightFragmentToTicketFragment(
        from: String,
        to: String,
        date: Date,
        countPassengers: Int
    )
}