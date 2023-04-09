package com.example.wethero.localdatabase

import android.content.Context
import androidx.room.*
import com.example.wethero.Model.Weather
import com.example.wethero.Model.Welcome
import com.example.wethero.favouriteModel.FavRecyclerModel
import com.example.wethero.favouritesViewModel.FavViewModel

@Database(entities = arrayOf(Welcome::class, FavRecyclerModel::class), version = 1)
@TypeConverters (Converter::class)
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