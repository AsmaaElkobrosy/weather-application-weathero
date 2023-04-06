package com.example.wethero.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wethero.Model.Daily
import com.example.wethero.databinding.DayRowBinding
import com.example.wethero.databinding.HoursRowBinding
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


class DailyAdapter ( var current: List<Daily>):
    RecyclerView.Adapter<DailyAdapter.DailyViewHolder>() {
    lateinit var context: Context
    lateinit var binding: DayRowBinding

    class DailyViewHolder(var binding: DayRowBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        context=parent.context
        val inflater: LayoutInflater =parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding= DayRowBinding.inflate(inflater,parent,false)
        return DailyAdapter.DailyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return current.size
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {


        val currentObj=current.get(position)
        val df = DecimalFormat("#.##")
        var temprature  =df.format(currentObj.temp.day - 273.15f)

        Glide.with(context).load("https://openweathermap.org/img/wn/${currentObj.weather?.get(0)?.icon}@2x.png").into(holder.binding.iconImgDaily)
        var timeHour = getCurrentTime(currentObj.dt.toInt())
        holder.binding.dtDaily.text= timeHour
        holder.binding.tempDaily.text = temprature.toString()+ " °C"
        holder.binding.humidityDaily.text = currentObj.humidity.toString()+ "%"



    }
}
@SuppressLint("SimpleDateFormat")
fun getCurrentTime(dt: Int) : String{
    var date= Date(dt*1000L)
    var sdf= SimpleDateFormat("hh:mm a")
    sdf.timeZone= TimeZone.getDefault()
    return sdf.format(date)
}