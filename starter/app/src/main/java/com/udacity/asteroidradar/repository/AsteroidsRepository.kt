package com.udacity.asteroidradar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.data.database.AsteroidsDatabase
import com.udacity.asteroidradar.data.database.model.asDomain
import com.udacity.asteroidradar.data.network.AsteroidsNeoNetwork
import com.udacity.asteroidradar.data.network.model.asDatabaseModel
import com.udacity.asteroidradar.domain.Asteroid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AsteroidsRepository(private val dataBase: AsteroidsDatabase) {

    private val network = AsteroidsNeoNetwork()

    val asteroids: LiveData<List<Asteroid>> = Transformations.map(dataBase.asteroidsDao.getAsteroids()) {
        it.map { asteroid ->
            asteroid.asDomain()
        }
    }

    suspend fun refreshAsteroids(startDate: String, endDate: String) {
        withContext(Dispatchers.IO) {
            try {
                val asteroidsList = network.refreshAsteroids(startDate, endDate)
                dataBase.asteroidsDao.insertAll(*asteroidsList.map { it.asDatabaseModel() }.toTypedArray())
            } catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }
}