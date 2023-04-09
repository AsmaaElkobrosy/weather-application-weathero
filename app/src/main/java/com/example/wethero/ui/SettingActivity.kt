package com.example.wethero.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.wethero.R
import com.example.wethero.view.HomeFragment
import com.example.wethero.view.MainActivity
import com.google.android.gms.location.*
import kotlinx.coroutines.*

class SettingActivity : AppCompatActivity() {

    lateinit var mFusedLocationClient: FusedLocationProviderClient
    var latText:String = ""
    var longText:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
//        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val radioButton = findViewById<RadioButton>(R.id.gps)
        radioButton.setOnClickListener {
           Toast.makeText(applicationContext,"${latText} and ${longText}",Toast.LENGTH_SHORT).show()
            println(latText+"and"+longText)
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("lat",latText )
            intent.putExtra("lon",longText )

            startActivity(intent)
//            val mFragmentManager = supportFragmentManager
//            val mFragmentTransaction = mFragmentManager.beginTransaction()
//            val mFragment = HomeFragment()
//
//            val mBundle = Bundle()
//            mBundle.putString("lat",latText )
//            mBundle.putString("lon",longText  )
//            mFragment.arguments = mBundle
//            mFragmentTransaction.add(R.id.Settingui, mFragment).commit()
        }


////ca2b01baf69d772e70734ccfdc4cb9cd
    }


    private val mLocatioCallBack: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val mLastLocation: Location? = locationResult.lastLocation
            latText= mLastLocation?.latitude.toString()
            longText = mLastLocation?.longitude.toString()
            // Declaring fragment manager from making data
            // transactions using the custom fragment
        }
    }


    private fun checkPermissions():Boolean {

        val result = ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        return result
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {
        val mLocationRequest = LocationRequest()
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        mLocationRequest.setInterval(0)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationClient.requestLocationUpdates(
            mLocationRequest,
            mLocatioCallBack,
            Looper.myLooper()
        )

    }


    @SuppressLint("MissingPermission")
    private fun getLastLocation(): Unit {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                requestNewLocationData()
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_SHORT).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)

            }
        } else {
            requestPermissions()
        }
    }
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
            101
        )
    }

    override fun onResume() {
        super.onResume()
        if (checkPermissions()) {
            getLastLocation()
        }
    }
}
