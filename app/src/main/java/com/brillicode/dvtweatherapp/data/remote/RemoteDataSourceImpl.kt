package com.brillicode.dvtweatherapp.data.remote

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

import com.brillicode.dvtweatherapp.data.service.ApiService
import com.brillicode.dvtweatherapp.util.constants.AppConstants
import com.brillicode.dvtweatherapp.util.constants.NetworkConstants

class RemoteDataSourceImpl(private val api: ApiService) : RemoteDataSource {
    override suspend fun getWeather(latitude: Double, longitude: Double) = api.getWeather(
        AppConstants.APP_ID, latitude, longitude, NetworkConstants.METRIC_UNIT
    )

    override suspend fun getForecast(latitude: Double, longitude: Double) = api.getForecast(
        AppConstants.APP_ID, latitude, longitude, NetworkConstants.METRIC_UNIT
    )
}