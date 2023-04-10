package com.example.wethero.view

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.wethero.Model.Repo
import com.example.wethero.Model.favouriteview.FavFragment
import com.example.wethero.R
import com.example.wethero.databinding.FragmentHomeBinding
import com.example.wethero.localdatabase.LocalSource
import com.example.wethero.network.RemoteSource
import com.example.wethero.viewmodel.DailyAdapter
import com.example.wethero.viewmodel.HomeViewModel
import com.example.wethero.viewmodel.HomeViewModelFactory
import com.example.wethero.viewmodel.HoursAdapter
import kotlinx.coroutines.launch
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


@Suppress("UNreachable code")
class HomeFragment : Fragment() {


    lateinit var binding: FragmentHomeBinding
    lateinit var myViewModel: HomeViewModel
    lateinit var myViewModelFactory: HomeViewModelFactory
    lateinit var hoursAdapter: HoursAdapter
    lateinit var latitude:String
    lateinit var longtude:String
    lateinit var mainActivity: MainActivity


//    private var _binding: FragmentHomeBinding? = null
//    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = MainActivity()
        val bundle = arguments
        val lat = bundle?.getString("lat")
        val lon = bundle?.getString("lon")
//        var lat = latitude
//        var lon = longtude
    println("i'm home"+lat+"   "+lon)
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.goToFav.setOnClickListener{
            var favFragment = FavFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentContainerHome, favFragment!!)
            fragmentTransaction.commit()
        }
        myViewModelFactory = HomeViewModelFactory(Repo.getInstance(RemoteSource.getINSTANCE(), LocalSource(requireContext())))
        myViewModel = ViewModelProvider(this.requireActivity(), myViewModelFactory)[HomeViewModel::class.java]

//        if(checkConnection()){
           if (lat != null && lon !=null) {
                myViewModel.getWeather( lat.toDouble() ,lon.toDouble(),"ca2b01baf69d772e70734ccfdc4cb9cd")
           }
//        }else{
//            myViewModel.getWeatherFromRoom()
//        }
//
//        myViewModel.currentWeather.observe(viewLifecycleOwner) {
//            hoursAdapter =
//            this.adapter =hoursAdapter.notifyDataSetChanged()
//            hoursAdapter = HoursAdapter { myViewModel.setWeather() }

//        }

viewLifecycleOwner.lifecycleScope.launch{
        myViewModel.currentWeather.collect{
if(it.lat!=0.0&&it.lon!=0.0){
            myViewModel.insertWeather(it)
            val df = DecimalFormat("#.##")
            var temprature  =df.format(it.current.temp - 273.15f)
            binding.cityText.text = it.timezone
            binding.TempraturDegree.text = temprature.toString() + " °C"
            binding.cloudsText.text = it.current.clouds.toString()
            binding.humidityText.text = it.current.humidity.toString() +" %"
            binding.pressureText.text = it.current.pressure.toString()
            binding.windSpeedText.text = "WIND SPEED    "+it.current.windSpeed.toString()
            Glide.with(requireActivity())
                .load("https://openweathermap.org/img/wn/${it.current.weather.get(0).icon}@2x.png")
                .into(binding.iconWeather)
            binding.currentDate.text = it.current.dt.toString()
            binding.hourlyRecycler.apply {
                this.adapter = HoursAdapter(it.hourly)
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            }
            binding.dailyRecycler.apply {
                this.adapter = DailyAdapter(it.daily)
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            }

            var timeHour = getCurrentTime(it.current.dt.toInt())
            binding.currentTime.text = timeHour

        }}}

        return root
    }
    fun getPlace( lat:String, lon: String){
         this.latitude = lat
         this.longtude = lon
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

