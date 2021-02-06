package com.quadrixsoft.interviewapplication.repository

import com.quadrixsoft.interviewapplication.repository.network.RetrofitService
import com.quadrixsoft.interviewapplication.repository.network.WeatherApi

object Repository {
    private val weatherApi = RetrofitService.invoke().create(WeatherApi::class.java)

    suspend fun getWeatherData() = weatherApi.fetchWeatherData()
}