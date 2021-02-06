package com.quadrixsoft.interviewapplication.ui.main

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.*
import com.quadrixsoft.interviewapplication.repository.Repository
import com.quadrixsoft.interviewapplication.repository.network.WeatherModel
import com.quadrixsoft.interviewapplication.repository.utils.PartOfTheDayCalculator
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

    private val welcomeTextResourceId: MutableLiveData<Int> by lazy {
        MutableLiveData(Repository.getWelcomeTextResourceId())
    }

    fun getWelcomeTextResourceId() : LiveData<Int> {
        return welcomeTextResourceId
    }

    private fun loadWeatherData() {
        viewModelScope.launch {
            val data = Repository.getWeatherData()
            withContext(Dispatchers.Main) {
                weather.value = data
            }
        }
    }

    fun setDefaultNightMode() {
        AppCompatDelegate.setDefaultNightMode(
            if(PartOfTheDayCalculator.isCurrentlyDaylight()) {
                AppCompatDelegate.MODE_NIGHT_NO
            } else {
                AppCompatDelegate.MODE_NIGHT_YES
            }
        )
    }
}