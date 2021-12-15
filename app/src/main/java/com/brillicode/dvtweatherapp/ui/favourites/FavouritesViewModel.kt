package com.brillicode.dvtweatherapp.ui.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavouritesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "There are no saved weathers at the moment."
    }
    val text: LiveData<String> = _text
}