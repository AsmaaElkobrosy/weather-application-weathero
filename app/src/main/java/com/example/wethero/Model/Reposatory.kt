package com.example.wethero.Model

import retrofit2.Response

interface Reposatory {

    suspend fun getAllStored():Welcome
    suspend fun insert(welcome: Welcome)
//    suspend fun delete(welcome: Welcome)
    suspend fun getAllWeathers( lat:Double,lon:Double,appid:String): Welcome
}