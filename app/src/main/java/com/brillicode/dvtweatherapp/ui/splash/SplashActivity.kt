package com.brillicode.dvtweatherapp.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import com.brillicode.dvtweatherapp.MainActivity
import com.brillicode.dvtweatherapp.R
import com.brillicode.dvtweatherapp.base.BaseActivity
import com.brillicode.dvtweatherapp.databinding.ActivitySplashBinding
import com.brillicode.dvtweatherapp.util.constants.AppConstants
import com.google.android.material.snackbar.Snackbar

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {
    private val splashViewModel by viewModels<SplashViewModel>()
    private lateinit var view: View;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        view = ActivitySplashBinding.inflate(layoutInflater).root
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
        val snack = Snackbar.make(
            view,
            getString(R.string.location_permission_denied_message),
            Snackbar.LENGTH_LONG
        )
        snack.setAction(getString(R.string.ok)) {
            finish()
        }
        snack.show()
    }

    private fun launchHomeScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}