package com.brillicode.dvtweatherapp.data.repository

import com.brillicode.dvtweatherapp.data.remote.RemoteDataSource

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


class RepositoryImpl(private val remoteDataSource: RemoteDataSource) : Repository {
    override suspend fun getWeather(type: String): List<Character> {
        TODO("Not yet implemented")
    }

    //    override suspend fun getCharacters(type: String) =
//        remoteDataSource.getCharacters(type)

}