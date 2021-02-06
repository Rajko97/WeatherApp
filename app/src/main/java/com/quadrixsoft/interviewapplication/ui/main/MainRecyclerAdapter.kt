package com.quadrixsoft.interviewapplication.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.quadrixsoft.interviewapplication.R
import com.quadrixsoft.interviewapplication.repository.network.WeatherModel
import java.text.SimpleDateFormat

class MainRecyclerAdapter : RecyclerView.Adapter<MainRecyclerAdapter.WeatherViewHolder>() {
    private val weatherFor24hList : ArrayList<WeatherModel.HourlyModel> = ArrayList(emptyList())

    fun submitData(newWeatherData: List<WeatherModel.HourlyModel>) {
        weatherFor24hList.clear()
        weatherFor24hList.addAll(newWeatherData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_main_item, parent, false))
    }

    inner class WeatherViewHolder(private val itemView: View): RecyclerView.ViewHolder(itemView) {
        private val tvAverageTemperature: TextView = itemView.findViewById(R.id.tvAvgTemperature)
        private val tvTime: TextView = itemView.findViewById(R.id.tvTime)
        private val tvDescription: TextView = itemView.findViewById(R.id.tvDesc)
        private val imageView: ImageView = itemView.findViewById(R.id.ivIcon)

        fun bind(data: WeatherModel.HourlyModel) {
            val temperature = String.format("%.1f°C", data.temp)
            val time = SimpleDateFormat("HH:mm").format(data.time*1000)

            tvAverageTemperature.text = temperature
            tvTime.text = time
            tvDescription.text = data.weather[0].description

            Glide
                .with(itemView.context)
                .load("http://openweathermap.org/img/w/"+data.weather[0].icon+".png")
                .centerCrop()
                .into(imageView)
        }
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(weatherFor24hList[position])
    }

    override fun getItemCount(): Int {
        return minOf(weatherFor24hList.size, 24)
    }
}