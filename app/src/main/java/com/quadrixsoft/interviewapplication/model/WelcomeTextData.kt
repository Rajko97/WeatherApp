package com.quadrixsoft.interviewapplication.model

import com.quadrixsoft.interviewapplication.R

data class WelcomeTextData(val greetingsStringResourceId : Int, val selectedCityName: String, val mainMessageResourceId : Int = R.string.welcome_header)