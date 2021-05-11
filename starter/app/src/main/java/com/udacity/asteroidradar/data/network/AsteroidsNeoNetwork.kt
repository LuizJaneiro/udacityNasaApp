package com.udacity.asteroidradar.data.network


import com.udacity.asteroidradar.data.network.api.Network
import com.udacity.asteroidradar.data.network.model.AsteroidResponse
import org.json.JSONObject
import java.lang.Exception

class AsteroidsNeoNetwork {
    suspend fun refreshAsteroids(startDate: String, endDate: String): ArrayList<AsteroidResponse> {
        try {
            val response = Network.asteroidNeoService.getAsteroids(startDate, endDate)
            return parseAsteroidsJsonResult(JSONObject(response))
        } catch (exception: Exception) {
            throw exception
        }
    }
}