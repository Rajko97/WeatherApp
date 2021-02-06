package com.quadrixsoft.interviewapplication.repository.utils

import android.annotation.SuppressLint
import com.luckycatlabs.sunrisesunset.SunriseSunsetCalculator
import com.luckycatlabs.sunrisesunset.dto.Location
import com.quadrixsoft.interviewapplication.utils.Constants
import java.text.SimpleDateFormat
import java.util.*

object PartOfTheDayCalculator {
    enum class PartOfTheDay {
        MORNING, AFTERNOON, EVENING, NIGHT
    }

    fun calculate() : PartOfTheDay {
        return when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
            in 0..11 -> PartOfTheDay.MORNING
            in 12..16 -> PartOfTheDay.AFTERNOON
            in 17..20 -> PartOfTheDay.EVENING
            else -> PartOfTheDay.NIGHT
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun isCurrentlyDaylight() : Boolean{
        val calculator = SunriseSunsetCalculator(Location(Constants.LATITUDE, Constants.LONGITUDE), "Serbia/Belgrade")

        val sdf = SimpleDateFormat("HH:mm")

        val sunrise = sdf.parse(calculator.getOfficialSunriseForDate(Calendar.getInstance()))
        val sunset = sdf.parse(calculator.getOfficialSunsetForDate(Calendar.getInstance()))
        val now = getCurrentTime()

        return (now >= sunrise && now < sunset)
    }

    @SuppressLint("SimpleDateFormat")
    private fun getCurrentTime() : Date {
        val sdfNow = SimpleDateFormat("H:m")
        val rightNow = Calendar.getInstance()
        val hours = rightNow.get(Calendar.HOUR_OF_DAY)
        val minutes = rightNow.get(Calendar.MINUTE)

        return sdfNow.parse("$hours:$minutes")
    }
}