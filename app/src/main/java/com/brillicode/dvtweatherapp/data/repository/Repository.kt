package com.brillicode.dvtweatherapp.data.repository

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

import com.brillicode.dvtweatherapp.base.BaseApiResponse
import com.brillicode.dvtweatherapp.data.model.ForecastResponse
import com.brillicode.dvtweatherapp.data.model.WeatherResponse
import com.brillicode.dvtweatherapp.data.service.ApiService
import com.brillicode.dvtweatherapp.util.network.NetworkConstants
import com.brillicode.dvtweatherapp.util.network.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(private val api: ApiService) :
    BaseApiResponse() {
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    suspend fun getForecast(
        latitude: Double,
        longitude: Double
    ): Flow<NetworkResult<ForecastResponse>> {
        return flow {
            emit(apiCall {
                api.getForecast(
                    NetworkConstants.APP_ID,
                    latitude,
                    longitude,
                    NetworkConstants.METRIC_UNIT
                )
            })
        }.flowOn(ioDispatcher)
    }

    suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): Flow<NetworkResult<WeatherResponse>> {
        return flow {
            emit(apiCall {
                api.getWeather(
                    NetworkConstants.APP_ID,
                    latitude,
                    longitude,
                    NetworkConstants.METRIC_UNIT
                )
            })
        }.flowOn(ioDispatcher)
    }
}