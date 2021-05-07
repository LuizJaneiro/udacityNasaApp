package com.udacity.asteroidradar.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.domain.Asteroid
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _asteroidsLiveData = MutableLiveData<List<Asteroid>>()
    private val _navigateToSelectedAsteroidLiveData = MutableLiveData<Asteroid?>()

    val asteroidsLiveData: LiveData<List<Asteroid>>
        get() = _asteroidsLiveData
    val navigateToSelectedAsteroidLiveData: LiveData<Asteroid?>
        get() = _navigateToSelectedAsteroidLiveData

    init {
        getAsteroids()
    }

    fun displayAsteroidDetails(asteroid: Asteroid) {
        _navigateToSelectedAsteroidLiveData.value = asteroid
    }

    fun displayAsteroidDetailsComplete() {
        _navigateToSelectedAsteroidLiveData.value = null
    }

    private fun getAsteroids() {
        viewModelScope.launch {
            val asteroids = mutableListOf<Asteroid>()
            for(i in 0 .. 10) {
                asteroids.add(Asteroid(
                        id = i.toLong(),
                        codename = "XPS (203234)",
                        distanceFromEarth = 2000.00,
                        absoluteMagnitude = 2.3,
                        closeApproachDate = "2020-02-02",
                        estimatedDiameter = 243.00,
                        isPotentiallyHazardous = i%2 == 0,
                        relativeVelocity = 232.3
                ))
            }
            _asteroidsLiveData.value = asteroids
        }
    }
}