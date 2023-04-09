package com.example.wethero.favouriteview

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wethero.Model.Hourly
import com.example.wethero.Model.Weather
import com.example.wethero.databinding.HoursRowBinding
import com.example.wethero.viewmodel.HoursAdapter
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class FavHoursAdapter ( var current: List<Hourly>):
    RecyclerView.Adapter<FavHoursAdapter.HoursViewHolder>() {
    lateinit var context: Context
    lateinit var binding: HoursRowBinding

//    lateinit var current :Current

    class HoursViewHolder (var binding :HoursRowBinding): RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoursViewHolder {
        context=parent.context
        val inflater: LayoutInflater =parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding= HoursRowBinding.inflate(inflater,parent,false)
        return HoursViewHolder(binding)
    }
    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: FavHoursAdapter.HoursViewHolder, position: Int) {
        val currentObj=current.get(position)

        Glide.with(context).load("https://openweathermap.org/img/wn/${currentObj.weather?.get(0)?.icon}@2x.png").into(holder.binding.iconImgHour)

        var timeHour = getCurrentTime(currentObj.dt.toInt())
//        Glide.with(context).load("https://openweathermap.org/img/wn/${currentObj.icon}@2x.png").into(holder.binding.iconImgHour)
        val df = DecimalFormat("#.##")
        var temprature  =df.format(currentObj.temp- 273.15f)

        holder.binding.tempHourly.text = temprature.toString()+ " °C"
        holder.binding.dateTimeHours.text= timeHour
    }

    override fun getItemCount(): Int {
        return current.size
    }

    @SuppressLint("SimpleDateFormat")
    fun getCurrentTime(dt: Int) : String{
        var date= Date(dt*1000L)
        var sdf= SimpleDateFormat("hh:mm a")
        sdf.timeZone=TimeZone.getDefault()
        return sdf.format(date)
    }
}

