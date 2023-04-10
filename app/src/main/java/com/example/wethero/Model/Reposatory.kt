package com.example.wethero.Model

import com.example.wethero.favouriteModel.FavRecyclerModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface Reposatory {

     fun getAllStored():Flow<Welcome>
    suspend fun insert(welcome: Welcome)
    suspend fun getAllWeathers( lat:Double,lon:Double,appid:String): Flow<Response<Welcome>>

     fun getAllFav():Flow<List<FavRecyclerModel>>

    suspend fun insertFav(favModel: FavRecyclerModel)
    suspend fun deleteFav(favModel: FavRecyclerModel):Int
}