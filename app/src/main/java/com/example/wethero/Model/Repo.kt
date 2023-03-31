package com.example.wethero.Model

import com.example.wethero.network.RemoteSource
import com.example.wethero.network.RemoteSourceInterface

class Repo private constructor(
    var remoteSource: RemoteSource
) :Reposatory{
    lateinit var welcome: Welcome

    companion object{
        private var instance:Repo?=null
        fun getInstance(
            remoteSource: RemoteSource
        ):Reposatory{
            return instance?: synchronized(this){
                val temp =Repo(
                    remoteSource
                )
                instance=temp
                temp
            }
        }
    }
    override suspend fun getAllStored(): List<Welcome> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(welcome: Welcome) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllWeathers(lat: Double, lon: Double, appid: String): Welcome {
        val response = remoteSource.getAllWeatherDetails(lat,lon,appid)
        if (response.isSuccessful){
            response.body().also {
                if (it != null) {
                    welcome = it
                }
            }
        }
        return welcome
    }
}