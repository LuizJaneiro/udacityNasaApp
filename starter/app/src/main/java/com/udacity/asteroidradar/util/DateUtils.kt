package com.udacity.asteroidradar.util

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

fun Date.toStringWithFormat(format: String) : String {
    return try {
        val formatter = SimpleDateFormat(format, Locale.getDefault())
        return formatter.format(this)
    } catch (exception: Exception) {
        exception.printStackTrace()
        ""
    }
}