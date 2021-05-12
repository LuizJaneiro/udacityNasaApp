package com.udacity.asteroidradar.data.network.model

import com.squareup.moshi.Json
import com.udacity.asteroidradar.domain.PictureOfTheDay

data class PictureOfDayResponse(@Json(name = "media_type") val mediaType: String, val title: String,
                                val url: String)

fun PictureOfDayResponse.asDomain() =
    PictureOfTheDay(
        mediaType,
        title,
        url
    )