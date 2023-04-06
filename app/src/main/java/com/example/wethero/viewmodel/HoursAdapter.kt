package com.example.wethero.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wethero.Model.Current
import com.example.wethero.Model.Hourly
import com.example.wethero.Model.Weather
import com.example.wethero.Model.Weathers
import com.example.wethero.databinding.HoursRowBinding
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class HoursAdapter ( var current: List<Hourly>):
    RecyclerView.Adapter<HoursAdapter.WeatherViewHolder>() {
    lateinit var context: Context
    lateinit var binding: HoursRowBinding

//    lateinit var current :Current

    class WeatherViewHolder (var binding :HoursRowBinding): RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        context=parent.context
        val inflater: LayoutInflater =parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding= HoursRowBinding.inflate(inflater,parent,false)
        return WeatherViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return current.size
    }


    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val currentObj=current.get(position)
//        Glide.with(context).load(currentObj.thumbnail).into(holder.binding.img)
//        holder.binding.nameTxt.text=currentObj.title
//        holder.binding.priceTxt.text=currentObj.price
//        holder.binding.addImg.setOnClickListener {  onClick(currentObj);}

        Glide.with(context).load("https://openweathermap.org/img/wn/${currentObj.weather?.get(0)?.icon}@2x.png").into(holder.binding.iconImgHour)
//        val max = Math.ceil(currentObj.temp.max).toInt()
//        val min = Math.ceil(currentObj.temp.min).toInt()
//        binding.tempDay.text="$max/$min°C"
//        val date= Date(currentObj.dt*1000L)
//        val sdf= SimpleDateFormat("d")
//        sdf.timeZone= TimeZone.getDefault()
//        val formatedData=sdf.format(date)
//        val calendar=Calendar.getInstance()
//        val intDay=formatedData.toInt()
//        calendar.set(Calendar.DAY_OF_MONTH,intDay)
//        val format=SimpleDateFormat(/* pattern = */ "EEEE")
//        val day=format.format(calendar.time)
//        binding.countryDay.text=day
//        holder.binding.daesDay.text= currentObj.weather.get(0).description

        var timeHour = getCurrentTime(currentObj.dt.toInt())
//        Glide.with(context).load("https://openweathermap.org/img/wn/${currentObj.icon}@2x.png").into(holder.binding.iconImgHour)
        val df = DecimalFormat("#.##")
        var temprature  =df.format(currentObj.temp- 273.15f)

        holder.binding.tempHourly.text = temprature.toString()+ " °C"
        holder.binding.dateTimeHours.text= timeHour
    }


    @SuppressLint("SimpleDateFormat")
    fun getCurrentTime(dt: Int) : String{
        var date= Date(dt*1000L)
        var sdf= SimpleDateFormat("hh:mm a")
        sdf.timeZone=TimeZone.getDefault()
        return sdf.format(date)
    }
}

    class ProductDiffUtil: DiffUtil.ItemCallback<Weather>(){
        override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
            return oldItem.id==newItem.id
        }
        override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
            return oldItem==newItem
        }
}