package com.udacity.asteroidradar.data.network.api

import com.udacity.asteroidradar.util.Constants
import kotlinx.coroutines.Deferred
import org.json.JSONObject
import retrofit2.http.GET
import retrofit2.http.Query

interface AsteroidNeoService {
    @GET("neo/rest/v1/feed")
    suspend fun getAsteroids(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ) : String
}