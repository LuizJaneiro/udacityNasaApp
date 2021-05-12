package com.udacity.asteroidradar.data.database.model

import androidx.room.TypeConverter
import com.udacity.asteroidradar.util.Constants
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Converters {
    var df: DateFormat = SimpleDateFormat(Constants.DATE_FORMAT, Locale.getDefault())

    @TypeConverter
    fun fromTimestamp(value: String?): Date? {
        return if (value != null) {
            try {
                return df.parse(value)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            null
        } else {
            null
        }
    }

    @TypeConverter
    fun dateToTimestamp(value: Date?): String? {
        return if (value == null) null else df.format(value)
    }
}