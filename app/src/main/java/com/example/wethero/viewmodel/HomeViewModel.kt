package com.example.wethero.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wethero.Model.Current
import com.example.wethero.Model.Reposatory
import com.example.wethero.Model.Welcome
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val repo:Reposatory): ViewModel() {
    private var _currentWeather= MutableStateFlow <Welcome>(Welcome(
        lat = 0.0, lon = 0.0, timezone = "", timezoneOffset = 0, current = Current(
            dt = 0,
            sunrise = null,
            sunset = null,
            temp = 0.0,
            feelsLike = 0.0,
            pressure = 0,
            humidity = 0,
            dewPoint = 0.0,
            uvi = 0.0,
            clouds = 0,
            visibility = 0,
            windSpeed = 0.0,
            windDeg = 0,
            windGust = 0.0,
            weather = listOf(),
            pop = null
        ), hourly = listOf(), daily = listOf()
    ))
    var currentWeather: MutableStateFlow<Welcome> =_currentWeather


    fun getWeatherFromRoom(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllStored().collect{result ->
                _currentWeather.value=result
            }
//            if(result!=null){
//                withContext(Dispatchers.Main){
//
//                }
//            }
            // products.postValue(repo.getAllStored())
        }

    }
    fun getWeather(lat: Double,lon: Double,appid: String){
        viewModelScope.launch{
            repo.getAllWeathers(lat,lon,appid).collect(){
                _currentWeather.value= it.body()!!
            }
        }
    }

    fun insertWeather(welcome:Welcome){
        viewModelScope.launch(Dispatchers.IO){
            repo.insert(welcome)
        }
    }
}
