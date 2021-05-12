package com.udacity.asteroidradar.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.udacity.asteroidradar.data.database.getDatabase
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.repository.AsteroidsRepository
import com.udacity.asteroidradar.util.Constants
import com.udacity.asteroidradar.util.toStringWithFormat
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val asteroidsRepository = AsteroidsRepository(database)

    private val _navigateToSelectedAsteroidLiveData = MutableLiveData<Asteroid?>()

    val asteroidsLiveData = asteroidsRepository.asteroids
    val pictureOfTheDayLiveData = asteroidsRepository.pictureOfTheDay
    val navigateToSelectedAsteroidLiveData: LiveData<Asteroid?>
        get() = _navigateToSelectedAsteroidLiveData

    init {
        viewModelScope.launch {
            asteroidsRepository.refreshAsteroids(
                Date()
            )
            setTodayAsteroids()
            asteroidsRepository.getPictureOfTheDay()
        }
    }

    fun displayAsteroidDetails(asteroid: Asteroid) {
        _navigateToSelectedAsteroidLiveData.value = asteroid
    }

    fun displayAsteroidDetailsComplete() {
        _navigateToSelectedAsteroidLiveData.value = null
    }

    fun setTodayAsteroids() {
        viewModelScope.launch {
            asteroidsRepository.setTodayAsteroids()
        }

    }

    fun setWeekAsteroids() {
        viewModelScope.launch {
            asteroidsRepository.setWeekAsteroids()
        }
    }

    fun setAllAsteroids() {
        viewModelScope.launch {
            asteroidsRepository.setAllAsteroids()
        }
    }
}