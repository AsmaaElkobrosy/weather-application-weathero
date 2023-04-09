package com.example.wethero.localdatabase

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wethero.Model.Welcome
import com.example.wethero.favouriteModel.FavRecyclerModel
import kotlinx.coroutines.flow.Flow

interface LocalSourceInterface {
        suspend fun insert(welcome: Welcome)
        suspend fun getAllStored():Welcome

        suspend fun getAllFav(): List<FavRecyclerModel>

        suspend fun insertFav(favModel: FavRecyclerModel)
        suspend fun deleteFav(favModel: FavRecyclerModel):Int

}