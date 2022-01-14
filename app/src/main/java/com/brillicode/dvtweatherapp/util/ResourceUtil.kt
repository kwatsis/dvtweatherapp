package com.brillicode.dvtweatherapp.util

import android.content.Context
import android.widget.ImageView
import com.brillicode.dvtweatherapp.R
import com.bumptech.glide.Glide

object ResourceUtil {

    fun loadResource(context: Context, weatherId: Int, imageView: ImageView?) {
        val resMap: HashMap<Int, Int> = HashMap()
        var drawableId: Int? = 0
        var colorId: Int? = 0
        when (weatherId) {
            800 -> resMap[R.drawable.ic_clear] = R.color.weather_sunny
            in 801..804 -> resMap[R.drawable.ic_partlysunny] = R.color.weather_cloudy
            in 500..531 -> resMap[R.drawable.ic_rain] = R.color.weather_rainy
        }

        resMap.forEach { (key, value) ->
            drawableId = key
            colorId = value
        }
        Glide.with(context).load(drawableId!!).into(imageView!!)
    }
}