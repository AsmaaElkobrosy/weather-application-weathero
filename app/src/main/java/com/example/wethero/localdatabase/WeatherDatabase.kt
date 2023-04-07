package com.example.wethero.localdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.wethero.Model.Weather

@Database(entities = [Weather::class], version = 1)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun getWeatherDao():WeatherDao
    companion object{
        @Volatile
        private var INSTANCE :WeatherDatabase?=null

        fun getInstance (ctx: Context):WeatherDatabase{
            return INSTANCE?: synchronized(this){
                val instance= Room.databaseBuilder(
                    ctx.applicationContext,WeatherDatabase::class.java,"weather_database_mvvm")
                    .build()
                INSTANCE=instance
                instance
            }
        }
    }
}