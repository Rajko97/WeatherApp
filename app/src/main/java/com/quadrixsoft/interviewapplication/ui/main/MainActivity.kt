package com.quadrixsoft.interviewapplication.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.quadrixsoft.interviewapplication.ui.main.MainViewModel
import com.quadrixsoft.interviewapplication.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvHelloWorld = findViewById<TextView>(R.id.tvHelloWorld)
        val viewModel: MainViewModel by viewModels()

        viewModel.getWeatherData().observe(this, {
            tvHelloWorld.text = it.toString()
        })
    }
}