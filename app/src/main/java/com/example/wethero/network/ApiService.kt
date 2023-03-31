package com.example.wethero.network

import com.example.wethero.Model.Weather
import com.example.wethero.Model.Welcome
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("onecall")
    suspend fun getWeatherDetails(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String = "ca2b01baf69d772e70734ccfdc4cb9cd"
    ): Response<Welcome>
}