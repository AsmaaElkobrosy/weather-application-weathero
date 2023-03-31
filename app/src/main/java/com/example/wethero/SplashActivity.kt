package com.example.wethero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wethero.network.RemoteSource
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {

//    lateinit var remoteSource: RemoteSource
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        remoteSource = RemoteSource.getINSTANCE()
//        CoroutineScope(Dispatchers.IO).launch { remoteSource.getAllWeatherDetails() }

        supportActionBar?.hide()
        CoroutineScope(Dispatchers.Main).launch {
            delay(4000L)
            startActivity(Intent(this@SplashActivity,SettingActivity::class.java))
            finish()
        }


    }
}