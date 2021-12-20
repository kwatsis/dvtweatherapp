package com.brillicode.dvtweatherapp.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import com.brillicode.dvtweatherapp.MainActivity
import com.brillicode.dvtweatherapp.R
import com.brillicode.dvtweatherapp.base.BaseActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {
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
        }, 3000)
    }

    override fun onGrantedPermission() { launchHomeScreen() }

    override fun onDeniedPermission() { launchHomeScreen() }

    private fun launchHomeScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}