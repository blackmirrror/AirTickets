package ru.blackmirrror.airtickets.common.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

object TextFormatter {
    fun Int.toBeautifulPrice(): String {
        val decimalFormat = DecimalFormat("#,###")
        val symbols = DecimalFormatSymbols.getInstance(Locale.getDefault())
            .apply { groupingSeparator = ' ' }

        decimalFormat.decimalFormatSymbols = symbols
        return decimalFormat.format(this) + " ₽"
    }
}