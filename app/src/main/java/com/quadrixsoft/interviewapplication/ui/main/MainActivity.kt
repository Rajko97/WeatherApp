package com.quadrixsoft.interviewapplication.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.quadrixsoft.interviewapplication.R

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBackground()
        setContentView(R.layout.activity_main)

        initWelcomeText()
        initWeather24h()
    }

    private fun initWeather24h() {
        val recyclerView : RecyclerView = findViewById(R.id.recyclerWeather24h)
        val adapter = MainRecyclerAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.getWeatherData().observe(this, { weatherData ->
            adapter.submitData(weatherData.hourly)
        })
    }

    private fun initWelcomeText() {
        val tvHelloWorld = findViewById <TextView>(R.id.tvHelloWorld)
        viewModel.getWelcomeTextResourceId().observe(this, {
            tvHelloWorld.text = getString(it)
        })
    }

    private fun initBackground() {
        viewModel.setDefaultNightMode()
    }
}