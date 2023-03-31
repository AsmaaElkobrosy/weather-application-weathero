package com.example.wethero.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName="weather_details")

data class Welcome (
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezoneOffset: Long,
    val current: Current,
    val hourly: List<Current>,
    val daily: List<Daily>
)

data class Current (
    val dt: Long,
    val sunrise: Long? = null,
    val sunset: Long? = null,
    val temp: Double,
    val feelsLike: Double,
    val pressure: Long,
    val humidity: Long,
    val dewPoint: Double,
    val uvi: Double,
    val clouds: Long,
    val visibility: Long,
    val windSpeed: Double,
    val windDeg: Long,
    val windGust: Double,
    val weather: List<Weather>,
    val pop: Long? = null
)

data class Weather (
    val id: Long,
    val main: Main,
    val description: String,
    val icon: Icon
)

enum class Description {
    ClearSky,
    FewClouds
}

enum class Icon {
    The01D,
    The01N,
    The02D
}

enum class Main {
    Clear,
    Clouds
}

data class Daily (
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val moonrise: Long,
    val moonset: Long,
    val moonPhase: Double,
    val temp: Temp,
    val feelsLike: FeelsLike,
    val pressure: Long,
    val humidity: Long,
    val dewPoint: Double,
    val wind_speed: Double,
    val windDeg: Long,
    val windGust: Double,
    val weather: List<Weather>,
    val clouds: Long,
    val pop: Long,
    val uvi: Double
)

data class FeelsLike (
    val day: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
)

data class Temp (
    val day: Double,
    val min: Double,
    val max: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
)




