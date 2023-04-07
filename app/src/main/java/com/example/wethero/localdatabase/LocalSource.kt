package com.example.wethero.localdatabase

import android.content.Context
import com.example.wethero.Model.Welcome

class LocalSource(context: Context):LocalSourceInterface{
    private val dao:WeatherDao by lazy {
        val db:WeatherDatabase=WeatherDatabase.getInstance(context)
        db.getWeatherDao()
    }
    override suspend fun insert(welcome: Welcome) {
        dao.insertWeather(welcome)
    }

    override suspend fun getAllStored(): Welcome{
        return dao.getWeatherDetails()
    }

    override suspend fun delete(welcome: Welcome) {
        dao.deleteWeather(welcome)
    }

}