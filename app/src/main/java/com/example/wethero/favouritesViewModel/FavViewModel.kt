package com.example.wethero.favouritesViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wethero.Model.Reposatory
import com.example.wethero.Model.Welcome
import com.example.wethero.favouriteModel.FavRecyclerModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavViewModel (private val repo: Reposatory): ViewModel() {
    private var _currentWeather= MutableLiveData <List<FavRecyclerModel>>()
    var currentWeather: LiveData<List<FavRecyclerModel>> =_currentWeather

init {
   getFavFromRoom()
}
    fun getFavFromRoom(){
        viewModelScope.launch(Dispatchers.IO) {
            val result=repo.getAllFav()
            if(result!=null){
                withContext(Dispatchers.Main){
                    _currentWeather.value=result
                }
            }
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
