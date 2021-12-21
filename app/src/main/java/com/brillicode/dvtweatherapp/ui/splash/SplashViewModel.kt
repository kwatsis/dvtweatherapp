package com.brillicode.dvtweatherapp.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashViewModel : ViewModel() {
    val isGranted = MutableLiveData<Boolean>()
    val isFromSplash = MutableLiveData<Boolean>()
}