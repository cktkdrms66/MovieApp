package com.weather.movieapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.weather.movieapplication.databinding.ItemLoadingBinding

class LoadingItemAdapter : ItemAdapter{

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ItemLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        return
    }

    inner class LoadingViewHolder(binding : ItemLoadingBinding): RecyclerView.ViewHolder(binding.root){

    }
}