package com.example.wethero.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wethero.Model.Reposatory
import com.example.wethero.Model.Welcome
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val repo:Reposatory): ViewModel() {
    private var _currentWeather= MutableLiveData <Welcome>()
    var currentWeather: LiveData<Welcome> =_currentWeather


    fun getWeatherFromRoom(){
        viewModelScope.launch(Dispatchers.IO) {
            val result=repo.getAllStored()
            if(result!=null){
                withContext(Dispatchers.Main){
                    _currentWeather.value=result
                }
            }
            // products.postValue(repo.getAllStored())
        }

    }
    fun getWeather(lat: Double,lon: Double,appid: String){
        viewModelScope.launch(Dispatchers.IO){
            _currentWeather.postValue(repo.getAllWeathers(lat,lon,appid))
        }
    }
}
