package com.brillicode.dvtweatherapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brillicode.dvtweatherapp.data.model.Forecast
import com.brillicode.dvtweatherapp.databinding.WeatherItemBinding
import javax.inject.Inject

class ForecastAdapter @Inject constructor() : RecyclerView.Adapter<MainViewHolder>() {
    var forecastList = mutableListOf<Forecast>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            WeatherItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val forecast = forecastList[position]
        holder.binding.stName.text = forecast.dt_txt
        //TODO [KJS - Add compound view here]
        //Glide.with(holder.itemView.context).load(movie.imageUrl).into(holder.binding.imageview)
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

class MainViewHolder(val binding: WeatherItemBinding) : RecyclerView.ViewHolder(binding.root)