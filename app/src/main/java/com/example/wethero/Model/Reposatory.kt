package com.example.wethero.Model

import com.example.wethero.favouriteModel.FavRecyclerModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface Reposatory {

    suspend fun getAllStored():Welcome
    suspend fun insert(welcome: Welcome)
    suspend fun getAllWeathers( lat:Double,lon:Double,appid:String): Welcome

    suspend fun getAllFav():List<FavRecyclerModel>

    suspend fun insertFav(favModel: FavRecyclerModel)
    suspend fun deleteFav(favModel: FavRecyclerModel):Int
}