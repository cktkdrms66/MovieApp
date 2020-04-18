package com.weather.movieapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.weather.movieapplication.data.MovieItem
import com.weather.movieapplication.databinding.ItemMovieBinding

class MovieItemAdapter : ItemAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        if(holder is MovieViewHolder){
            holder.bind(item)
        }
    }

    inner class MovieViewHolder(var binding : ItemMovieBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(movie : ViewType){
            if(movie is MovieItem){
                binding.movie = movie
            }
        }
    }
}