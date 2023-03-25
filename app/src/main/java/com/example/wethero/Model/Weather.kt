package com.example.wethero.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName="weather_details")
data class Weather(@PrimaryKey var id:String, var temp:Int, var timezone:String, var humidity:Int, var clouds:Int,
                   var wind_speed:Int, var stock:String, var brand:String, var category:String, var thumbnail:String) :
    Serializable {



}