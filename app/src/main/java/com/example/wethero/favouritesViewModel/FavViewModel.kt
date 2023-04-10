package com.example.wethero.favouritesViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wethero.Model.Reposatory
import com.example.wethero.Model.Welcome
import com.example.wethero.favouriteModel.FavRecyclerModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavViewModel (private val repo: Reposatory): ViewModel() {
    private var _currentWeather= MutableStateFlow <List<FavRecyclerModel>>(listOf())
    var currentWeather: StateFlow<List<FavRecyclerModel>> =_currentWeather

init {
   getFavFromRoom()
}
    fun getFavFromRoom(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllFav().collect{   result->
                withContext(Dispatchers.Main){
                    _currentWeather.value=result
                }
            }
//            if(result!=null){
//                withContext(Dispatchers.Main){
//
//                }
//            }
            // products.postValue(repo.getAllStored())
        }

    }
//    fun getWeather(lat: Double,lon: Double,appid: String){
//        viewModelScope.launch(Dispatchers.IO){
//            _currentWeather.postValue(repo.getAllWeathers(lat,lon,appid))
//        }
//    }

    fun insertFav(favModel: FavRecyclerModel){
        viewModelScope.launch(Dispatchers.IO){
            repo.insertFav(favModel)
            getFavFromRoom()
        }
    }

    fun deleteFav(favModel: FavRecyclerModel){
        viewModelScope.launch(Dispatchers.IO){
            repo.deleteFav(favModel)
            getFavFromRoom()
        }
    }

}
