package com.udacity.asteroidradar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.data.database.AsteroidsDatabase
import com.udacity.asteroidradar.data.database.model.asDomain
import com.udacity.asteroidradar.data.network.AsteroidsNeoNetwork
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

    suspend fun refreshAsteroids() {
        withContext(Dispatchers.IO) {

        }
    }
}