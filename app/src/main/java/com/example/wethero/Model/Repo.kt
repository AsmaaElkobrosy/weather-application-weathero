package com.example.wethero.Model

import android.util.Log
import com.example.wethero.favouriteModel.FavRecyclerModel
import com.example.wethero.localdatabase.LocalSource
import com.example.wethero.network.RemoteSource
import com.example.wethero.network.RemoteSourceInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class Repo private constructor(
    var remoteSource: RemoteSource,
    var localSource: LocalSource
) :Reposatory{
    lateinit var welcome: Welcome

    companion object{
        @Volatile
        private var instance:Repo?=null
        fun getInstance(
            remoteSource: RemoteSource,
            localSource: LocalSource
        ):Reposatory{
            return instance?: synchronized(this){
                val temp =Repo(
                    remoteSource,
                    localSource
                )
                instance=temp
                temp
            }
        }
    }

    override fun getAllStored(): Flow<Welcome> {
        return localSource.getAllStored()
    }

    override suspend fun insert(welcome: Welcome) {
        localSource.insert(welcome)
    }



//    override suspend fun delete(welcome: Welcome) {
//        localSource.delete(welcome)
//    }


    override suspend fun getAllWeathers(lat: Double, lon: Double, appid: String): Flow<Response<Welcome>> {
//        val response =
//        if (response.isSuccessful){
//            response.body().also {
//                if (it != null) {
//                    welcome = it
//                }
//            }
//        }else{
//            Log.i("TAG", "getAllWeathers: ${response.code()} ${response.body()}")
//        }
        return remoteSource.getAllWeatherDetails(lat,lon,appid)
    }

    override fun getAllFav(): Flow<List<FavRecyclerModel>> {
        return localSource.getAllFav()
    }

    override suspend fun insertFav(favModel: FavRecyclerModel) {
        localSource.insertFav(favModel)
    }

    override suspend fun deleteFav(favModel: FavRecyclerModel): Int {
        return localSource.deleteFav(favModel)
    }
}