package com.quadrixsoft.interviewapplication.ui.main

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.*
import com.quadrixsoft.interviewapplication.R
import com.quadrixsoft.interviewapplication.model.WelcomeTextData
import com.quadrixsoft.interviewapplication.repository.Repository
import com.quadrixsoft.interviewapplication.repository.network.WeatherModel
import com.quadrixsoft.interviewapplication.repository.utils.PartOfTheDayCalculator
import com.quadrixsoft.interviewapplication.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {
    private val weather: MutableLiveData<WeatherModel> by lazy {
        MutableLiveData(WeatherModel(emptyList())).also {
            loadWeatherData("Niš")
        }
    }

    fun getWeatherData(): LiveData<WeatherModel> {
        return weather
    }

    val welcomeTextData : MutableLiveData<WelcomeTextData> by lazy {
        MutableLiveData(WelcomeTextData(Repository.getWelcomeTextResourceId(), "Niš"))
    }

    fun onError(query: String) {
        welcomeTextData.postValue(WelcomeTextData(Repository.getWelcomeTextResourceId(), query, R.string.welcome_header_not_found))
    }

    fun loadWeatherData(selectedCityName : String, latitude : Double = Constants.LATITUDE, longitude : Double = Constants.LONGITUDE) {
        viewModelScope.launch {
            val data = Repository.getWeatherData(latitude, longitude)
            withContext(Dispatchers.Main) {
                weather.value = data
                welcomeTextData.value = WelcomeTextData(Repository.getWelcomeTextResourceId(), selectedCityName)
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