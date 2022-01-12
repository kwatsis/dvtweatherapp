package com.brillicode.dvtweatherapp.data.service

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

import com.brillicode.dvtweatherapp.data.model.ForecastResponse
import com.brillicode.dvtweatherapp.data.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("forecast")
    suspend fun getForecast(
        @Query("appid") appId: String,
        @Query("lon") longitude: Double,
        @Query("lat") latitude: Double,
        @Query("units") units: String,
    ): Response<ForecastResponse>

    @GET("weather")
    suspend fun getWeather(
        @Query("appid") appId: String,
        @Query("lon") longitude: Double,
        @Query("lat") latitude: Double,
        @Query("units") units: String,
    ): Response<WeatherResponse>
}