package com.udacity.asteroidradar.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.data.database.getDatabase
import com.udacity.asteroidradar.repository.AsteroidsRepository
import retrofit2.HttpException
import java.util.*

class RefreshDataWorker(appContext: Context, params: WorkerParameters)
    : CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val dataBase = getDatabase(applicationContext)
        val repository = AsteroidsRepository(dataBase)
        return try {
            repository.refreshAsteroids(Date())
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}