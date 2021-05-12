package com.udacity.asteroidradar.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.util.Constants
import com.udacity.asteroidradar.util.toStringWithFormat
import java.util.*

@Entity
data class DatabaseAsteroid(
    @PrimaryKey
    val id: Long,
    val codename: String,
    val closeApproachDate: Date,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
)

fun DatabaseAsteroid.asDomain() =
    Asteroid(
        id,
        codename,
        closeApproachDate.toStringWithFormat(Constants.API_QUERY_DATE_FORMAT),
        absoluteMagnitude,
        estimatedDiameter,
        relativeVelocity,
        distanceFromEarth,
        isPotentiallyHazardous
    )
