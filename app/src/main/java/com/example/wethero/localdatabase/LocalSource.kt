package com.example.wethero.localdatabase

import android.content.Context
import com.example.wethero.Model.Welcome
import com.example.wethero.favouriteModel.FavRecyclerModel
import kotlinx.coroutines.flow.Flow

class LocalSource(context: Context):LocalSourceInterface{
    private val dao:WeatherDao by lazy {
        val db:WeatherDatabase= WeatherDatabase.getInstance(context)
        db.getWeatherDao()
    }
    override suspend fun insert(welcome: Welcome) {
        dao.insertWeather(welcome)
    }

    override suspend fun getAllStored(): Welcome{
        return dao.getWeatherDetails()
    }

    override suspend fun getAllFav(): List<FavRecyclerModel> {
        return dao.getAllFavourites()
    }

    override suspend fun insertFav(favModel: FavRecyclerModel) {
        dao.insertFav(favModel)
    }

    override suspend fun deleteFav(favModel: FavRecyclerModel): Int {
        return dao.deleteFav(favModel)
    }

//    override suspend fun delete() {
//        dao.deleteWeather()
//    }

}