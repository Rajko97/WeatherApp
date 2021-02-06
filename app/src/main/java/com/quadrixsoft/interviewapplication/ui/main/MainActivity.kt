package com.quadrixsoft.interviewapplication.ui.main

import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.quadrixsoft.interviewapplication.R
import com.quadrixsoft.interviewapplication.utils.LanguageConverter.Companion.convertCyrillicToLatin

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var geocoder: Geocoder
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MainRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBackground()
        setContentView(R.layout.activity_main)

        initWelcomeText()
        initWeather24h()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.menu_main, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        geocoder = Geocoder(this)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null) {
                    try {
                        val address = geocoder.getFromLocationName(query, 1)
                        if (address.size > 0) {
                            recyclerView.scrollToPosition(0)
                            viewModel.loadWeatherData(
                                address[0].locality.convertCyrillicToLatin(),
                                address[0].latitude,
                                address[0].longitude
                            )
                            searchView.clearFocus()
                        } else {
                            throw Exception()
                        }
                    } catch (exception: Exception) {
                        adapter.submitData(emptyList())
                        viewModel.onError(query)
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
        return true
    }

    private fun initWeather24h() {
        recyclerView = findViewById(R.id.recyclerWeather24h)
        adapter = MainRecyclerAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.getWeatherData().observe(this, { weatherData ->
            adapter.submitData(weatherData)
        })
    }

    private fun initWelcomeText() {
        val tvHelloWorld = findViewById <TextView>(R.id.tvHelloWorld)
        viewModel.welcomeTextData.observe(this, {
            tvHelloWorld.text = getString(
                it.mainMessageResourceId,
                getString(it.greetingsStringResourceId),
                it.selectedCityName
            )
        })
    }

    private fun initBackground() {
        viewModel.setDefaultNightMode()
    }
}