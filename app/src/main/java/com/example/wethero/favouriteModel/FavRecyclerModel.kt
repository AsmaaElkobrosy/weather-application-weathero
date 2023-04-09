package com.example.wethero.favouriteModel

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName="fav_Room")
data class FavRecyclerModel (
    @PrimaryKey val lat: String,
    val lon: String,
    val city :String
): Serializable{}