package com.quadrixsoft.interviewapplication.repository

import com.quadrixsoft.interviewapplication.R
import com.quadrixsoft.interviewapplication.repository.network.*
import com.quadrixsoft.interviewapplication.repository.utils.PartOfTheDayCalculator
import kotlinx.coroutines.Dispatchers

object Repository {
    private val weatherApi = RetrofitService.invoke().create(WeatherApi::class.java)

    suspend fun getWeatherData(latitude : Double, longitude : Double) : ResultWrapper<WeatherModel> {
        return NetworkHelper.safeApiCall(Dispatchers.IO) {
            weatherApi.fetchWeatherData(lat = latitude, lon =longitude)
        }
    }

    fun getWelcomeTextResourceId(): Int {
        return when (PartOfTheDayCalculator.calculate()) {
            PartOfTheDayCalculator.PartOfTheDay.MORNING -> R.string.message_good_morning
            PartOfTheDayCalculator.PartOfTheDay.AFTERNOON -> R.string.message_good_afternoon
            PartOfTheDayCalculator.PartOfTheDay.EVENING -> R.string.message_good_evening
            else -> R.string.message_good_night
        }
    }
}