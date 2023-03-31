package com.example.wethero.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wethero.Model.Reposatory

class HomeViewModelFactory(private val repo:Reposatory): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            HomeViewModel(repo) as T
        }else{
            throw java.lang.IllegalArgumentException("ViewModel Class Not Found")
        }
    }
}