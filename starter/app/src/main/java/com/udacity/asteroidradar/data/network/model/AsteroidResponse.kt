package com.udacity.asteroidradar.data.network.model

import com.udacity.asteroidradar.data.database.model.DatabaseAsteroid

data class AsteroidResponse(
    val id: Long,
    val codename: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
)

fun AsteroidResponse.asDatabaseModel() =
    DatabaseAsteroid(
        id,
        codename,
        closeApproachDate,
        absoluteMagnitude,
        estimatedDiameter,
        relativeVelocity,
        distanceFromEarth,
        isPotentiallyHazardous
    )