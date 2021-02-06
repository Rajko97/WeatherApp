package com.quadrixsoft.interviewapplication.ui.main

import androidx.lifecycle.*
import com.quadrixsoft.interviewapplication.repository.Repository
import com.quadrixsoft.interviewapplication.repository.network.WeatherModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {
    private val weather: MutableLiveData<WeatherModel> by lazy {
        MutableLiveData(WeatherModel(emptyList())).also {
            loadWeatherData()
        }
    }

    fun getWeatherData(): LiveData<WeatherModel> {
        return weather
    }

    private fun loadWeatherData() {
        viewModelScope.launch {
            val data = Repository.getWeatherData()
            withContext(Dispatchers.Main) {
                weather.value = data
            }
        }
    }
}