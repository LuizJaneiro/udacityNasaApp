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
    val navigateToSelectedAsteroidLiveData: LiveData<Asteroid?>
        get() = _navigateToSelectedAsteroidLiveData

    init {
        viewModelScope.launch {
            val calendar = Calendar.getInstance()
            val actualDate = calendar.time
            calendar.add(Calendar.DAY_OF_YEAR, 7)
            val sevenDaysBefore = calendar.time
            asteroidsRepository.refreshAsteroids(
                actualDate.toStringWithFormat(Constants.API_QUERY_DATE_FORMAT),
                sevenDaysBefore.toStringWithFormat(Constants.API_QUERY_DATE_FORMAT)
            )
        }
    }

    fun displayAsteroidDetails(asteroid: Asteroid) {
        _navigateToSelectedAsteroidLiveData.value = asteroid
    }

    fun displayAsteroidDetailsComplete() {
        _navigateToSelectedAsteroidLiveData.value = null
    }
}