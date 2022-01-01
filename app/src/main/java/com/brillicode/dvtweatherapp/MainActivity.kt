package com.brillicode.dvtweatherapp

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.brillicode.dvtweatherapp.base.BaseActivity
import com.brillicode.dvtweatherapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        navView.setupWithNavController(navController)
    }

    override fun onDeniedPermission() {
        Toast.makeText(
            this,
            R.string.location_permission_denied_message,
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onRestart() {
        super.onRestart()
        validatePermission()
    }
}