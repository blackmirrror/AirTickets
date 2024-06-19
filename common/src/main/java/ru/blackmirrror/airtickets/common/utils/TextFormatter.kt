package ru.blackmirrror.airtickets.common.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

object TextFormatter {
    fun priceToPatternString(price: Int?): String {
        if (price == null)
            return ""
        val decimalFormat = DecimalFormat("#,###")
        val symbols = DecimalFormatSymbols.getInstance(Locale.getDefault())
            .apply { groupingSeparator = ' ' }

        decimalFormat.decimalFormatSymbols = symbols
        return decimalFormat.format(price) + " ₽"
    }

    fun timesToString(times: List<String>): String {
        return times.joinToString(separator = " ")
    }

    fun dateConvertToTime(date: Date?): String {
        if (date == null)
            return "-:-"
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        return dateFormat.format(date)
    }

    fun hoursBetweenDates(start: Date?, end: Date?): String {
        if (start == null || end == null)
            return "- ч в пути"
        else {
            val diffInMillies = end.time - start.time
            val hours = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS)
            return "$hours ч в пути"
        }
    }

    fun toDateWithPassengers(date: Date, count: Int): String {
        val dateFormat = SimpleDateFormat("d MMMM", Locale("ru", "RU"))

        return "${dateFormat.format(date)}, $count пассажир"
    }

    fun getDayOfWeek(year: Int, month: Int, day: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val days = arrayOf("вс", "пн", "вт", "ср", "чт", "пт", "сб")
        return days[calendar.get(Calendar.DAY_OF_WEEK) - 1]
    }

    fun getMonthName(month: Int): String {
        val months = arrayOf(
            "янв",
            "фев",
            "мар",
            "апр",
            "май",
            "июн",
            "июл",
            "авг",
            "сен",
            "окт",
            "ноя",
            "дек"
        )
        return months[month]
    }
}