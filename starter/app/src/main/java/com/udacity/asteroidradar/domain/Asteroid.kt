package com.udacity.asteroidradar.domain

import android.content.Context
import android.os.Parcelable
import com.udacity.asteroidradar.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Asteroid(val id: Long, val codename: String, val closeApproachDate: String,
                    val absoluteMagnitude: Double, val estimatedDiameter: Double,
                    val relativeVelocity: Double, val distanceFromEarth: Double,
                    val isPotentiallyHazardous: Boolean) : Parcelable {
                        fun getImageDescription(context: Context): String {
                            return if(this.isPotentiallyHazardous) {
                                context.getString(R.string.potentially_hazardous_asteroid_image)
                            } else {
                                context.getString(R.string.not_hazardous_asteroid_image)
                            }
                        }
                    }