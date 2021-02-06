package com.quadrixsoft.interviewapplication.repository.network

import com.quadrixsoft.interviewapplication.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface WeatherApi {
        @GET("/data/2.5/onecall")
        suspend fun fetchWeatherData(
            @Query("lat") lat : Double = Constants.LATITUDE,
            @Query("lon") lon : Double = Constants.LONGITUDE,
            @Query("exclude") exclude : String = "minutely,daily,alerts",
            @Query("appid") appId : String = Constants.API_KEY,
            @Query("units") units : String = "metric",
            @Query("lang") lang : String = Locale.getDefault().language
        ) : WeatherModel
}