package com.example.wethero.favouritesViewModel

import android.location.Address
import android.location.Geocoder
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.annotation.NonNull
import com.example.wethero.R
import com.example.wethero.databinding.FragmentMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException

class MapsFragment : Fragment() {
    lateinit var binding: FragmentMapsBinding
    lateinit var mapFragment: SupportMapFragment
    lateinit var mMaps :GoogleMap
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private val callback = OnMapReadyCallback { googleMap ->

        val egypt = LatLng(26.820553, 30.802498)
        googleMap.addMarker(MarkerOptions().position(egypt).title("Marker in Egypt"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(egypt))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMapsBinding.inflate(inflater,container,false)
        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(callback)
        mapInitialize()
        return binding.root
    }

    private fun mapInitialize() {
        val locationRequest :LocationRequest = LocationRequest()
        locationRequest.setInterval(500)
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        locationRequest.setSmallestDisplacement(16F)
        locationRequest.setFastestInterval(3000)

        binding.searchTextFav.setOnEditorActionListener (TextView.OnEditorActionListener{ v, actionId, event ->
            if(actionId== EditorInfo.IME_ACTION_SEARCH
                || actionId == EditorInfo.IME_ACTION_DONE){
                goToSearchLoaction()
            }
            return@OnEditorActionListener true
            false
        })

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())
    }

    private fun goToSearchLoaction() {
        var searchLocation = binding.searchTextFav.text.toString()
        var geoCoder :Geocoder = Geocoder(requireContext())
        var list: List<Address> = ArrayList()
        try {
            list = geoCoder.getFromLocationName(searchLocation,1) as List<Address>
        }catch (e: IOException){
            e.printStackTrace()
        }
        if(list.size>0){
            var address:Address = list.get(0)
            var location: String = address.adminArea
            var latitude :Double = address.latitude
            var longitude :Double = address.longitude
//            gotoLatLng(latitude,longitude,17f)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }


}