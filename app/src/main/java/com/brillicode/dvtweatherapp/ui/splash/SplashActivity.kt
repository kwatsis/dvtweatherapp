package com.brillicode.dvtweatherapp.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import com.brillicode.dvtweatherapp.MainActivity
import com.brillicode.dvtweatherapp.R
import com.brillicode.dvtweatherapp.base.BaseActivity
import com.brillicode.dvtweatherapp.util.constants.AppConstants

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {
    private val splashViewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        Handler(Looper.getMainLooper()).postDelayed({
            validatePermission()
        }, AppConstants.TIMEOUT)
    }

    override fun onGrantedPermission() {
        splashViewModel.isFromSplash.postValue(true)
        splashViewModel.isGranted.postValue(true)
        launchHomeScreen() //TODO - KJ - Verify this logic
    }

    override fun onDeniedPermission() {
       finish() //TODO REPLACE THIS LOGIC WITH A SNACK BAR WHICH THE POSITIVE ACTION finish()ES THE ACTIVITY
    }

    private fun launchHomeScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}