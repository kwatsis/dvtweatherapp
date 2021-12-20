package com.brillicode.dvtweatherapp

import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.brillicode.dvtweatherapp.base.BaseActivity
import com.brillicode.dvtweatherapp.databinding.ActivityMainBinding
import com.brillicode.dvtweatherapp.util.Constants
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val latitude = deviceLat;
    private val longitude = deviceLong;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        validatePermission()

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a setvalidatePermission() of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_favorites
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onDeniedPermission() {
        Log.d(Constants.TAG + " MainActivity", "Latitude: $latitude\nLongitude:$longitude")
    }
}