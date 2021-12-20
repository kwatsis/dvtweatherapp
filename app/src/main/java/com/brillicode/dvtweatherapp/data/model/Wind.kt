package com.brillicode.dvtweatherapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Wind(
    val speed: Double,
    val deg: Int,
    val gust: Double
) : Parcelable