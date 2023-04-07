package com.example.wethero.Model

import com.example.wethero.localdatabase.LocalSource
import com.example.wethero.network.RemoteSource
import com.example.wethero.network.RemoteSourceInterface

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

    override suspend fun getAllStored(): Welcome {
        return localSource.getAllStored()
    }

    override suspend fun insert(welcome: Welcome) {
        localSource.insert(welcome)
    }

    override suspend fun delete(welcome: Welcome) {
        localSource.delete(welcome)
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