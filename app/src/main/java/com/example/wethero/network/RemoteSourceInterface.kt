package com.example.wethero.network

import com.example.wethero.Model.Weather
import com.example.wethero.Model.Welcome
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RemoteSourceInterface {
    suspend fun getAllWeatherDetails(lat: Double, lon: Double, appid: String): Flow<Response<Welcome>>
}