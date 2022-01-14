package com.brillicode.dvtweatherapp

/**
 * Copyright 2021 Kwatsinyana Sesotlo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 **/

import android.os.Bundle
import androidx.activity.viewModels
import com.brillicode.dvtweatherapp.base.BaseActivity
import com.brillicode.dvtweatherapp.databinding.ActivityMainBinding
import com.brillicode.dvtweatherapp.ui.adapter.ForecastAdapter
import com.brillicode.dvtweatherapp.util.WeatherViewsUtils
import com.brillicode.dvtweatherapp.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    @Inject
    lateinit var adapter: ForecastAdapter
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rcvForecasts.adapter = adapter

        viewModel.weather.observe(this, {
            val response = it!!.data
            binding.container.setBackgroundResource(R.drawable.sea_rainy)

            val main = response!!.main
            val weathers = response.weather!!
            val currentTemp = main.temp.toInt().toString()

            binding.tvMainTemp.text = WeatherViewsUtils.addDegreeSymbol(this, currentTemp)
            binding.tvMainFeel.text = weathers[0].main

            binding.tvMinTemp.text =
                WeatherViewsUtils.addDegreeSymbol(this, main.temp_min.toInt().toString())
            binding.tvCurrentTemp.text = WeatherViewsUtils.addDegreeSymbol(this, currentTemp)
            binding.tvMaxTemp.text =
                WeatherViewsUtils.addDegreeSymbol(this, main.temp_max.toInt().toString())
        })

        viewModel.forecast.observe(this, {
            val forecastResponse = it!!.data!!
            adapter.setForecasts(forecastResponse.list)
        })
        viewModel.fetchWeatherForecast(deviceLong, deviceLong)

        validatePermission()
    }

    override fun onGrantedPermission() {

    }

    override fun onDeniedPermission() {
        val snack = Snackbar.make(
            binding.root,
            getString(R.string.location_permission_denied_message),
            Snackbar.LENGTH_LONG
        )
        snack.setAction(getString(R.string.ok)) {
            finish()
        }
        snack.show()
    }

    override fun onRestart() {
        super.onRestart()
        validatePermission()
    }
}