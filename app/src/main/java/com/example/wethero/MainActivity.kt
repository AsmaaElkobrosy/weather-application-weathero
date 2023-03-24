package com.example.wethero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import com.example.wethero.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            toggle= ActionBarDrawerToggle(this@MainActivity,drawerLayout,R.string.open,R.string.close)
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "WeatherO"
            navController.itemIconTintList = ContextCompat.getColorStateList(this@MainActivity, R.color.main1_blue)
            navController.setNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.home_nav -> {Toast.makeText(this@MainActivity,"home clicked",Toast.LENGTH_SHORT).show()}
                    R.id.fav_nav -> {Toast.makeText(this@MainActivity,"fav clicked",Toast.LENGTH_SHORT).show()}
                    R.id.alert_nav -> {Toast.makeText(this@MainActivity,"alert clicked",Toast.LENGTH_SHORT).show()}
                    R.id.settings_nav -> {Toast.makeText(this@MainActivity,"setting clicked",Toast.LENGTH_SHORT).show()}
                }
                true
            }
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            true
        }
        return super.onOptionsItemSelected(item)
    }
}