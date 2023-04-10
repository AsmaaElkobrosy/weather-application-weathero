package com.example.wethero.network

import android.util.Log
import com.example.wethero.Model.Weather
import com.example.wethero.Model.Welcome
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.Response

class RemoteSource :RemoteSourceInterface{
    val api:ApiService by lazy {
        RetrofitHelper.retrofitInstance.create(ApiService::class.java)
    }

    companion object{
        private var instance: RemoteSource? = null
        fun getINSTANCE(): RemoteSource {
            return instance ?: synchronized(this){
                val temp= RemoteSource()
                instance =temp
                temp
            }
        }
    }


    override suspend fun getAllWeatherDetails(lat: Double, lon: Double, appid: String): Flow<Response<Welcome>> {
//        val response = api.getWeatherDetails(lat.toString(),lon.toString())
//        GlobalScope.launch {
//
////        Log.i("TAG","getAllWeatherDetails"+ (response.body()?.current?.weather?.get(0)?.description))
//        }

        return  flow{ emit(api.getWeatherDetails(lat.toString(),lon.toString()))}
    }

}