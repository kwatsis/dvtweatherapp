package com.brillicode.dvtweatherapp

import android.app.Application
import com.brillicode.dvtweatherapp.di.dataSourceModule
import com.brillicode.dvtweatherapp.di.networkModule
import com.brillicode.dvtweatherapp.di.repositoryModule
import com.brillicode.dvtweatherapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WeatherApp)
            modules(dataSourceModule)
            modules(repositoryModule)
            modules(viewModelModule)
            modules(networkModule)
        }
    }
}