package com.example.wethero.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.wethero.Model.Repo
import com.example.wethero.databinding.FragmentHomeBinding
import com.example.wethero.network.RemoteSource
import com.example.wethero.viewmodel.HomeViewModel
import com.example.wethero.viewmodel.HomeViewModelFactory
import com.example.wethero.viewmodel.HoursAdapter


@Suppress("UNreachable code")
class HomeFragment : Fragment() {


    lateinit var binding: FragmentHomeBinding
    lateinit var myViewModel: HomeViewModel
    lateinit var myViewModelFactory: HomeViewModelFactory
    lateinit var hoursAdapter: HoursAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater)
        val root: View = binding.root

        myViewModelFactory = HomeViewModelFactory(
            Repo.getInstance(RemoteSource.getINSTANCE())
        )
        myViewModel = ViewModelProvider(this, myViewModelFactory).get(HomeViewModel::class.java)

        myViewModel.currentWeather.observe(viewLifecycleOwner) {
//            hoursAdapter.submitList(it.hourly)
//            this.adapter =hoursAdapter.notifyDataSetChanged()
//            hoursAdapter = HoursAdapter { myViewModel.setWeather() }
            binding.hourlyRecycler.apply {
                this.adapter = hoursAdapter
                layoutManager = LinearLayoutManager(context)


            }
        }



    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        val root :View = binding.root


        myViewModelFactory = HomeViewModelFactory(Repo.getInstance(RemoteSource.getINSTANCE()))
        myViewModel = ViewModelProvider(this.requireActivity(),myViewModelFactory)[HomeViewModel::class.java]



        myViewModel.currentWeather.observe(viewLifecycleOwner){
            binding.cityText.text = it.timezone
            binding.TempraturDegree.text = it.current.temp.toString()
            binding.cloudsText.text = it.current.clouds.toString()
            binding.humidityText.text = it.current.humidity.toString()
            binding.pressureText.text = it.current.pressure.toString()
            binding.windSpeedText.text = "WIND SPEED    "+it.current.windSpeed.toString()
            Glide.with(requireActivity())
                .load("https://openweathermap.org/img/wn/${it.current.weather.get(0).icon}@2x.png")
                .into(binding.iconWeather)
            binding.currentDate.text = it.current.dt.toString()
//            binding.hourlyRecycler.apply {
//                layoutManager = LinearLayoutManager(context)
//                this.adapter = HoursAdapter(it.hourly)
//            }
        }

        return root
    }
}