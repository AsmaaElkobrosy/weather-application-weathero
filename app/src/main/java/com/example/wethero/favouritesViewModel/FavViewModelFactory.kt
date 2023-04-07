package com.example.wethero.favouritesViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wethero.Model.Reposatory

class FavViewModelFactory (private val repo: Reposatory): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(FavViewModel::class.java)){
            FavViewModel(repo) as T
        }else{
            throw java.lang.IllegalArgumentException("ViewModel Class Not Found")
        }
    }
}