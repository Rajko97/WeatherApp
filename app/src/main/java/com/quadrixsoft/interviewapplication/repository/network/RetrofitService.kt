package com.quadrixsoft.interviewapplication.repository.network

import com.quadrixsoft.interviewapplication.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    companion object {
        @Volatile
        private var INSTANCE: Retrofit? = null

        operator fun invoke(): Retrofit = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildRetrofit().also { INSTANCE = it }
        }

        private fun buildRetrofit() =
            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()
                ).build()
    }
}
