package com.quadrixsoft.interviewapplication.repository.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherModel(
    @SerializedName("hourly")
    @Expose
    val hourly : List<HourlyModel>
) {
    data class HourlyModel(
        @SerializedName("temp")
        @Expose
        val temp : Double
    )
}