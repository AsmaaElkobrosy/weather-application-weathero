package com.example.wethero.localdatabase

import androidx.room.*
import com.example.wethero.Model.Welcome
import com.example.wethero.favouriteModel.FavRecyclerModel
import com.example.wethero.favouritesViewModel.FavViewModel
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather_details")
     fun getWeatherDetails():Flow<Welcome>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWeather(welcome: Welcome)


    //favourites

    @Query("SELECT * FROM fav_Room")
     fun getAllFavourites(): Flow<List<FavRecyclerModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFav(favModel: FavRecyclerModel)
    @Delete
    suspend fun deleteFav(favModel: FavRecyclerModel):Int
}