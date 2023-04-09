package com.example.wethero.favouriteview

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.wethero.Model.Repo
import com.example.wethero.R
import com.example.wethero.databinding.FragmentDetailsFavBinding
import com.example.wethero.databinding.FragmentHomeBinding
import com.example.wethero.localdatabase.LocalSource
import com.example.wethero.network.RemoteSource
import com.example.wethero.viewmodel.DailyAdapter
import com.example.wethero.viewmodel.HomeViewModel
import com.example.wethero.viewmodel.HomeViewModelFactory
import com.example.wethero.viewmodel.HoursAdapter
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


class DetailsFavFragment : Fragment() {

    lateinit var binding: FragmentDetailsFavBinding
    lateinit var myViewModel: HomeViewModel
    lateinit var myViewModelFactory: HomeViewModelFactory
    lateinit var hoursAdapter: HoursAdapter
    lateinit var latitude:String
    lateinit var longtude:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = arguments
        val lat = bundle?.getString("lat")
        val lon = bundle?.getString("lon")
        println(lat+"   "+lon)
        binding = FragmentDetailsFavBinding.inflate(inflater, container, false)
        val root: View = binding.root
        myViewModelFactory = HomeViewModelFactory(Repo.getInstance(RemoteSource.getINSTANCE(), LocalSource(requireContext())))
        myViewModel = ViewModelProvider(this.requireActivity(), myViewModelFactory)[HomeViewModel::class.java]

        myViewModel.getWeather( lat!!.toDouble() ,lon!!.toDouble(),"ca2b01baf69d772e70734ccfdc4cb9cd")


        myViewModel.currentWeather.observe(viewLifecycleOwner){
            val df = DecimalFormat("#.##")
            var temprature  =df.format(it.current.temp - 273.15f)
            binding.favTempraturDegree.text = temprature.toString() + " °C"
            binding.favCloudsText.text = it.current.weather.get(0).description
            binding.favHumidityText.text = it.current.humidity.toString() +" %"
            binding.favPressureText.text = it.current.pressure.toString()
            binding.favWindSpeedText.text = "WIND SPEED    "+it.current.windSpeed.toString()
            Glide.with(requireActivity())
                .load("https://openweathermap.org/img/wn/${it.current.weather.get(0).icon}@2x.png")
                .into(binding.favIconWeather)
            binding.favCurrentDate.text = it.current.dt.toString()
            binding.favHourlyRecycler.apply {
                this.adapter = HoursAdapter(it.hourly)
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            }
            binding.favDailyRecycler.apply {
                this.adapter = DailyAdapter(it.daily)
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            }

            var timeHour = getCurrentTime(it.current.dt.toInt())
            binding.favCurrentTime.text = timeHour

        }
        return root
    }
    @SuppressLint("SimpleDateFormat")
    fun getCurrentTime(dt: Int) : String{
        var date= Date(dt*1000L)
        var sdf= SimpleDateFormat("hh:mm a")
        sdf.timeZone= TimeZone.getDefault()
        return sdf.format(date)
    }
    fun checkConnection(): Boolean {
        val cm = context?.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return if (activeNetwork != null) {true}else{false}}

}