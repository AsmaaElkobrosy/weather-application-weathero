package com.example.wethero.localdatabase

import androidx.room.TypeConverter
import com.example.wethero.Model.Current
import com.example.wethero.Model.Daily
import com.example.wethero.Model.Hourly
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {
    @TypeConverter
    fun fromCurrentToString(current: Current):String{
        return  Gson().toJson(current)
    }
    @TypeConverter
    fun fromStringToCurrent(currentString: String):Current{
        return Gson().fromJson(currentString,Current::class.java)
    }
    @TypeConverter
    fun fromDailyToString(daily: Daily): String{
        return Gson().toJson(daily)
    }
    @TypeConverter
    fun fromStringToDaily(dailyString: String): Daily {
        return Gson().fromJson(dailyString, Daily::class.java)
    }
    @TypeConverter
    fun fromHourlyToString(hourly: Hourly): String{
        return Gson().toJson(hourly)
    }
    @TypeConverter
    fun fromStringToHourly(hourlyString: String): Hourly? {
        return Gson().fromJson(hourlyString, Hourly::class.java)
    }
    @TypeConverter
    fun fromHourlyListToString(hourlyList: List<Hourly?>?): String? {
        if (hourlyList == null) {
            return null
        }
        val gson = Gson()
        val type= object :
            TypeToken<List<Hourly?>?>() {}.type
        return gson.toJson(hourlyList, type)
    }
    @TypeConverter
    fun fromStringToHourlyList(hourlyString: String?): List<Hourly>? {
        if (hourlyString == null) {
            return null
        }
        val gson = Gson()
        val type = object :
            TypeToken<List<Daily?>?>() {}.type
        return gson.fromJson<List<Hourly>>(hourlyString, type)
    }
    @TypeConverter
    fun fromDailyListToString(dailyList: List<Daily?>?): String? {
        if (dailyList == null) {
            return null
        }
        val gson = Gson()
        val type= object :
            TypeToken<List<Daily?>?>() {}.type
        return gson.toJson(dailyList, type)
    }

    @TypeConverter
    fun fromStringToDailyList(dailyString: String?): List<Daily>? {
        if (dailyString == null) {
            return null
        }
        val gson = Gson()
        val type = object :
            TypeToken<List<Daily?>?>() {}.type
        return gson.fromJson<List<Daily>>(dailyString, type)
    }
//    @TypeConverter
//    fun fromAlertToString(alert: Alert): String{
//        return Gson().toJson(alert)
//    }
//    @TypeConverter
//    fun fromStringToAlert(alertString: String): Alert {
//        return Gson().fromJson(alertString, Alert::class.java)
//    }
//    @TypeConverter
//    fun fromAlertListToString(alertList:List<Alert?>?): String? {
//        if (alertList == null) {
//            return null
//        }
//        val gson = Gson()
//        val type= object :
//            TypeToken<List<Alert?>?>() {}.type
//        return gson.toJson(alertList, type)
//    }

//    @TypeConverter
//    fun fromStringToAlertList(alertString: String?): List<Alert>? {
//        if (alertString == null) {
//            return null
//        }
//        val gson = Gson()
//        val type = object :
//            TypeToken<List<Alert?>?>() {}.type
//        return gson.fromJson<List<Alert>>(alertString, type)
//    }
}