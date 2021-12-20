package com.brillicode.dvtweatherapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Clouds(
    val all: Double
) : Parcelable