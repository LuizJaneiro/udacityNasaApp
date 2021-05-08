package com.udacity.asteroidradar.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udacity.asteroidradar.data.network.api.Network
import com.udacity.asteroidradar.data.network.model.AsteroidResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class AsteroidsNeoNetwork {

    private val _asteroids = MutableLiveData<List<AsteroidResponse>>()

    val asteroids: LiveData<List<AsteroidResponse>>
        get() = _asteroids


    suspend fun refreshAsteroids(startDate: String, endDate: String) {
        withContext(Dispatchers.IO) {
            try {
                val response = Network.asteroidNeoService.getAsteroids(startDate, endDate).await()
                val asteroidsList = parseAsteroidsJsonResult(response)
                _asteroids.value = asteroidsList
            } catch (exception: Exception) {
                throw exception
            }
        }
    }
}