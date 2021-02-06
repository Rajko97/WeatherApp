package com.quadrixsoft.interviewapplication.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.quadrixsoft.interviewapplication.R
import com.quadrixsoft.interviewapplication.model.Weather24HData
import com.quadrixsoft.interviewapplication.utils.Constants

class MainRecyclerAdapter : RecyclerView.Adapter<MainRecyclerAdapter.WeatherViewHolder>() {
    private val weatherFor24hList : ArrayList<Weather24HData> = ArrayList(emptyList())

    fun submitData(newWeatherData: List<Weather24HData>) {
        weatherFor24hList.clear()
        weatherFor24hList.addAll(newWeatherData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_main_item, parent, false))
    }

    inner class WeatherViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val tvAverageTemperature: TextView = itemView.findViewById(R.id.tvAvgTemperature)
        private val tvTime: TextView = itemView.findViewById(R.id.tvTime)
        private val tvDescription: TextView = itemView.findViewById(R.id.tvDesc)
        private val imageView: ImageView = itemView.findViewById(R.id.ivIcon)

        fun bind(data: Weather24HData) {
            tvAverageTemperature.text = data.temperature
            tvTime.text = data.time
            tvDescription.text = data.description

            Glide
                .with(itemView.context)
                .load(Constants.IMAGE_API+data.iconPath+".png")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
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