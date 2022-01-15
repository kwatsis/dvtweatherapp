package com.brillicode.dvtweatherapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brillicode.dvtweatherapp.data.model.Forecast
import com.brillicode.dvtweatherapp.databinding.ForecastItemBinding
import com.brillicode.dvtweatherapp.util.WeatherViewsUtils
import javax.inject.Inject

class ForecastAdapter @Inject constructor() : RecyclerView.Adapter<MainViewHolder>() {
    var forecastList = mutableListOf<Forecast>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            ForecastItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val forecast = forecastList[position]
        val binding = holder.binding
        val context = holder.itemView.context

        binding.tvForecastDay.text = forecast.dt_txt
        binding.tvForecastTemp.text =
            WeatherViewsUtils.withDegreeSymbol(context, forecast.main.temp_max.toString())

        WeatherViewsUtils.modifyView(context, forecast.weather[0].id, binding.imgForecast, null)
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setForecasts(forecasts: List<Forecast>) {
        this.forecastList = forecasts.toMutableList()
        notifyDataSetChanged()
    }
}

class MainViewHolder(val binding: ForecastItemBinding) : RecyclerView.ViewHolder(binding.root)