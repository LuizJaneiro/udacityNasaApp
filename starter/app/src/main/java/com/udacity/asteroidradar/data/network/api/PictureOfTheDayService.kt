package com.udacity.asteroidradar.data.network.api

import com.udacity.asteroidradar.data.network.model.PictureOfDayResponse
import com.udacity.asteroidradar.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureOfTheDayService {
    @GET("planetary/apod")
    suspend fun getPictureOfTheDay(@Query("api_key") apiKey: String = Constants.API_KEY): PictureOfDayResponse
}