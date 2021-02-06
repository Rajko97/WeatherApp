package com.quadrixsoft.interviewapplication.repository

import android.annotation.SuppressLint
import com.quadrixsoft.interviewapplication.model.Weather24HData
import com.quadrixsoft.interviewapplication.repository.network.WeatherModel
import com.quadrixsoft.interviewapplication.utils.LanguageConverter.Companion.convertCyrillicToLatin
import java.text.SimpleDateFormat

class Mapper {
    companion object {
        @SuppressLint("SimpleDateFormat")
        fun WeatherModel.convertToDomainObject(): List<Weather24HData> {
            return this.hourly.map {
                Weather24HData(
                    String.format("%.1f°C", it.temp),
                    SimpleDateFormat("HH:mm").format(it.time * 1000),
                    it.weather[0].description.convertCyrillicToLatin(),
                    it.weather[0].icon

                )
            }
        }
    }
}