package com.example.wethero.localdatabase

import androidx.room.*
import com.example.wethero.Model.Welcome

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather_details")
    suspend fun getWeatherDetails():Welcome

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWeather(welcome: Welcome)

    @Delete
    suspend fun deleteWeather(welcome: Welcome):Int
}