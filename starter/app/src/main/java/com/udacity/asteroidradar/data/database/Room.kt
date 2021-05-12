package com.udacity.asteroidradar.data.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.udacity.asteroidradar.data.database.model.Converters
import com.udacity.asteroidradar.data.database.model.DatabaseAsteroid
import java.util.*

@Dao
interface AsteroidsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg asteroids: DatabaseAsteroid)

    @Query("select * from DatabaseAsteroid order by closeApproachDate asc")
    fun getAsteroids(): List<DatabaseAsteroid>

    @Query("select * from DatabaseAsteroid where closeApproachDate = :date order by closeApproachDate asc")
    fun getAsteroidsFromDate(date: Date): List<DatabaseAsteroid>

    @Query("select * from DatabaseAsteroid where closeApproachDate between :startDate and :endDate order by closeApproachDate asc")
    fun getAsteroidsBetween(startDate: Date, endDate: Date): List<DatabaseAsteroid>
}

@Database(entities = [DatabaseAsteroid::class], version = 1)
@TypeConverters(Converters::class)
abstract class AsteroidsDatabase: RoomDatabase() {
    abstract val asteroidsDao: AsteroidsDao
}

private lateinit var INSTANCE: AsteroidsDatabase

fun getDatabase(context: Context): AsteroidsDatabase {
    synchronized(AsteroidsDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                AsteroidsDatabase::class.java,
                "asteroids")
                .build()
        }
    }
    return INSTANCE
}