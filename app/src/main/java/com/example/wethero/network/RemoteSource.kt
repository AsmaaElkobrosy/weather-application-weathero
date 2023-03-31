package com.example.wethero.network

import android.util.Log
import com.example.wethero.Model.Weather
import com.example.wethero.Model.Welcome
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

    override suspend fun getAllWeatherDetails(lat: Double, lon: Double, appid: String): Response<Welcome> {
        val response = api.getWeatherDetails("26.8205517","30.8024967")
        Log.i("TAG","getAllWeatherDetails"+ (response.body()?.current?.weather?.get(0)?.description))

        return response
    }

}