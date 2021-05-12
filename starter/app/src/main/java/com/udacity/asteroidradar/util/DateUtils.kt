package com.udacity.asteroidradar.util

import java.lang.Exception
import java.lang.IllegalArgumentException
import java.text.SimpleDateFormat
import java.util.*

fun Date.toStringWithFormat(format: String): String {
    try {
        val formatter = SimpleDateFormat(format, Locale.getDefault())
        return formatter.format(this)
    } catch (exception: Exception) {
        exception.printStackTrace()
    }
    return ""
}

fun String.toDateWithFormat(format: String): Date {
    try {
        val formatter = SimpleDateFormat(format, Locale.getDefault())
        return formatter.parse(this) ?: throw IllegalArgumentException()
    } catch (exception: Exception) {
        exception.printStackTrace()
    }
    return Date()
}

fun Date.getDateAfter(days: Int): Date {
    try {
        val calendar = Calendar.getInstance()
        calendar.time = this
        calendar.add(Calendar.DAY_OF_YEAR, days)
        return calendar.time
    } catch(exception: Exception) {
        exception.printStackTrace()
    }
    return this
}

fun Date.getDateBefore(days: Int): Date {
    try {
        val calendar = Calendar.getInstance()
        calendar.time = this
        calendar.add(Calendar.DAY_OF_YEAR, days * -1)
        return calendar.time
    } catch(exception: Exception) {
        exception.printStackTrace()
    }
    return this
}

fun getTodayDate(): Date {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    return calendar.time
}