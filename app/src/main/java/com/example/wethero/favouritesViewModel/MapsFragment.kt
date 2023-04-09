package com.example.wethero.favouritesViewModel

import android.location.Address
import android.location.Geocoder
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.wethero.Model.favouriteview.FavFragment
import com.example.wethero.R
import com.example.wethero.databinding.FragmentMapsBinding
import com.example.wethero.favouriteModel.FavRecyclerModel
import com.example.wethero.favouriteview.FavouriteActivity
import com.example.wethero.view.HomeFragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdate

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
//    lateinit var marker: Marker
    lateinit var userlist :ArrayList<FavRecyclerModel>
    lateinit var addBtn :Button
    lateinit var markerOptions: MarkerOptions 
    lateinit var latitude :String
    lateinit var longitude :String
    lateinit var location: String
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private val callback = OnMapReadyCallback { googleMap ->
        mMaps = googleMap
        val egypt = LatLng(26.820553, 30.802498)
        mMaps.addMarker(MarkerOptions().position(egypt).title("Marker in Egypt"))
        mMaps.moveCamera(CameraUpdateFactory.newLatLng(egypt))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMapsBinding.inflate(inflater,container,false)
        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(callback)
//        userlist = ArrayList()
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
            return@OnEditorActionListener false
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
             location= address.countryName
            var lat = address.latitude
            var lon = address.longitude
             latitude  = address.latitude.toString()
             longitude  = address.longitude.toString()

            gotoLatLng(lat,lon,17f)
//            if (marker !=null){
//                marker.remove()
//            }
//            mMaps = GoogleMap()
//            markerOptions = MarkerOptions()

//            markerOptions.title(location)
//            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
//            mMaps.addMarker((MarkerOptions().position(latLng))
        }

    }

    private fun gotoLatLng(latitude: Double, longitude: Double, f: Float) {
        var latLng: LatLng = LatLng(latitude, longitude)
        var update:CameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng,17f)
        mMaps.clear()
        mMaps.addMarker((MarkerOptions().position(latLng)))
        mMaps.animateCamera(update)
        println(latitude.toString()+" "+longitude.toString()+"  "+location)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addToFav.setOnClickListener(View.OnClickListener {
            val mFragmentManager = requireActivity().supportFragmentManager
            val mFragmentTransaction = mFragmentManager.beginTransaction()
            val mFragment = FavFragment()

            val mBundle = Bundle()
            mBundle.putString("lat",latitude )
            mBundle.putString("lon",longitude  )
            mBundle.putString("city",location  )
            mFragment.arguments = mBundle
            mFragmentTransaction.add(R.id.map, mFragment).commit()
//            Navigation.findNavController(view).navigate(sendData)
        })
    }


}