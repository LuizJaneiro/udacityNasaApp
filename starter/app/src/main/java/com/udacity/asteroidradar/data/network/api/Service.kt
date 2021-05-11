package com.udacity.asteroidradar.data.network.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.udacity.asteroidradar.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory


object Network {
    private val retrofitScalars = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val asteroidNeoService: AsteroidNeoService = retrofitScalars.create(AsteroidNeoService::class.java)
}