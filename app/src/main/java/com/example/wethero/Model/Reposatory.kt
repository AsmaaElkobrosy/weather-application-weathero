package com.example.wethero.Model

import retrofit2.Response

interface Reposatory {

    suspend fun getAllStored():List<Weather>
    suspend fun delete(weather: Weather)
    suspend fun getAllWeathers(): Response<Weathers>
}