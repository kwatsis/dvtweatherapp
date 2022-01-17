package com.brillicode.dvtweatherapp.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.brillicode.dvtweatherapp.R
import com.bumptech.glide.Glide
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.HashMap

object AppUtils {

    fun modifyView(context: Context, weatherId: Int, imageView: ImageView?, view: View?) {
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
        view?.setBackgroundResource(colorId!!)

        if (imageView != null) {
            Glide.with(context).load(drawableId!!).into(imageView)
        }
    }

    fun withDegreeSymbol(context: Context, reading: String): String {
        return reading + context.getString(R.string.unicode_degrees)
    }

    fun getDayOfWeek(dateString: String): String {
        val formatter = DateTimeFormatter.ofPattern(AppConstants.FORMATTER_PATTERN)
        val date = LocalDate.parse(dateString, formatter)
        val c = Calendar.getInstance()
        c[2022, 1, 10, 0, 0] = 0
        c.add(Calendar.DAY_OF_MONTH, date.dayOfMonth)
        return java.lang.String.format(AppConstants.DAY_OF_WEEK_FORMAT, c)
    }

}