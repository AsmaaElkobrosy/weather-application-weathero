package com.example.wethero.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.wethero.Model.favouriteview.FavFragment
import com.example.wethero.R
import com.example.wethero.databinding.ActivityMainBinding
import com.example.wethero.ui.SettingActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val lat= intent.getStringExtra("lat")
        val lon= intent.getStringExtra("lon")
        if (lat != null && lon !=null) {
           println(lat+"  "+lon)
            val mFragmentManager = supportFragmentManager
            val mFragmentTransaction = mFragmentManager.beginTransaction()
            val mFragment = HomeFragment()

            val mBundle = Bundle()
            mBundle.putString("lat",lat)
            mBundle.putString("lon",lon)
            mFragment.arguments = mBundle
            mFragmentTransaction.add(R.id.drawer_layout, mFragment).commit()
        }


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            toggle= ActionBarDrawerToggle(this@MainActivity,drawerLayout,
                R.string.open,
                R.string.close
            )
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "WeatherO"
//            supportActionBar?.setIcon(getDrawable(R.id.fav_icon_weather))
            navView.itemIconTintList = ContextCompat.getColorStateList(this@MainActivity,
                R.color.main1_blue
            )
            navView.setNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.home_nav -> {Toast.makeText(this@MainActivity,"Home clicked",Toast.LENGTH_SHORT).show()}
//                    R.id.fav_nav -> { replaceFragment(FavFragment())}
                    R.id.alert_nav -> {Toast.makeText(this@MainActivity,"alert clicked",Toast.LENGTH_SHORT).show()}
                    R.id.settings_nav -> {val intent = Intent(this@MainActivity, SettingActivity::class.java)
                        startActivity(intent)}
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

    /*@Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }*/
//    fun replaceFragment(fragment: Fragment?) {
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.fragmentContainerHome, fragment!!)
//        fragmentTransaction.commit()
//    }
}