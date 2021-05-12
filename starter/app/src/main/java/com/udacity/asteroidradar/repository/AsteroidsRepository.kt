package com.udacity.asteroidradar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udacity.asteroidradar.data.database.AsteroidsDatabase
import com.udacity.asteroidradar.data.database.model.asDomain
import com.udacity.asteroidradar.data.network.AsteroidsNeoNetwork
import com.udacity.asteroidradar.data.network.model.asDatabaseModel
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class AsteroidsRepository(private val dataBase: AsteroidsDatabase) {

    private val network = AsteroidsNeoNetwork()

    private val _asteroids = MutableLiveData<List<Asteroid>?>()

    val asteroids: LiveData<List<Asteroid>?>
        get() = _asteroids

    suspend fun refreshAsteroids(startDate: Date) {
        withContext(Dispatchers.IO) {
            try {
                val asteroidsList =
                    network.refreshAsteroids(
                        startDate.toStringWithFormat(Constants.API_QUERY_DATE_FORMAT),
                        startDate.getDateAfter(7).toStringWithFormat(Constants.API_QUERY_DATE_FORMAT)
                    )
                dataBase.asteroidsDao.insertAll(*asteroidsList.map { it.asDatabaseModel() }.toTypedArray())
                setTodayAsteroids()
            } catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun setTodayAsteroids() {
        withContext(Dispatchers.IO) {
            _asteroids.postValue(null)
            _asteroids.postValue(dataBase.asteroidsDao.getAsteroidsFromDate(getTodayDate()).map {
                    it.asDomain()
                }
            )
        }
    }

    suspend fun setWeekAsteroids() {
        withContext(Dispatchers.IO) {
            _asteroids.postValue(null)
            _asteroids.postValue(dataBase.asteroidsDao.getAsteroidsBetween(getTodayDate(), getTodayDate().getDateAfter(7)).map {
                    it.asDomain()
                }
            )
        }
    }

    suspend fun setAllAsteroids() {
        withContext(Dispatchers.IO) {
            _asteroids.postValue(null)
            _asteroids.postValue(dataBase.asteroidsDao.getAsteroids().map {
                    it.asDomain()
                }
            )
        }
    }
}