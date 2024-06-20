package ru.blackmirrror.airtickets.data.room.utils

import androidx.room.TypeConverter
import java.text.DateFormat
import java.util.Date


internal class Converters {
    @TypeConverter
    fun fromTimestamp(value: String?): Date? {
        return value?.let { DateFormat.getDateInstance().parse(value) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): String? {
        return date?.time?.let { DateFormat.getDateInstance().format(it) }
    }
}