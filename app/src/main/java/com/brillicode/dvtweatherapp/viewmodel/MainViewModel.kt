package com.brillicode.dvtweatherapp.viewmodel
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

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brillicode.dvtweatherapp.data.model.ForecastResponse
import com.brillicode.dvtweatherapp.data.model.WeatherResponse
import com.brillicode.dvtweatherapp.data.repository.Repository
import com.brillicode.dvtweatherapp.util.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: Repository) : ViewModel() {
    val forecast = MutableLiveData<NetworkResult<ForecastResponse>>()
    val progress = MutableLiveData<Boolean>()
    val weather = MutableLiveData<NetworkResult<WeatherResponse>>()

    fun fetchWeatherForecast(long: Double, lat: Double) {
        viewModelScope.launch {
            progress.value = true
            supervisorScope {
                val forecastCall = async {
                    repo.getForecast(lat, long).collect { value ->
                        forecast.value = value
                    }
                }
                forecastCall.await()

                val weatherCall = async {
                    repo.getWeather(lat, long).collect { value ->
                        weather.value = value
                    }
                }
                weatherCall.await()

                progress.value = false
            }
        }
    }
}