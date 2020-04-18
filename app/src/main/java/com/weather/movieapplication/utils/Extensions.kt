package com.weather.movieapplication.utils

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.weather.movieapplication.R
import com.weather.movieapplication.data.MovieItem
import com.weather.movieapplication.ui.adapter.MovieAdapter
import com.weather.movieapplication.ui.adapter.MovieItemAdapter
import com.weather.movieapplication.ui.adapter.ViewType

@BindingAdapter("loadImg")
fun loadImg(view : ImageView, image : String){

    var urlFull = "https://image.tmdb.org/t/p/w500/${image}"
    if(TextUtils.isEmpty(urlFull)){
        Picasso.get().load(R.mipmap.ic_launcher).into(view)
    }else{
        println(urlFull)
        Picasso.get().load(urlFull).centerCrop().fit().into(view)
    }
}

@BindingAdapter("bindItems")
fun bindItems(recyclerView : RecyclerView, items : ObservableArrayList<ViewType>){
    var movieAdapter = recyclerView.adapter
    if(movieAdapter is MovieAdapter){
        movieAdapter.items = items
    }
}

