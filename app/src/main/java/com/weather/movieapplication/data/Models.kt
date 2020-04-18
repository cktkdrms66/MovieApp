package com.weather.movieapplication.data

import android.os.Parcel
import android.os.Parcelable
import com.weather.movieapplication.ui.adapter.AdapterType
import com.weather.movieapplication.ui.adapter.ViewType
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieItem(var title: String, var poster_path: String) : ViewType,Parcelable {
    override fun getViewType(): Int {
        return AdapterType.MOVIE
    }
}

@Parcelize
data class MovieList(var page: Int?, val results : List<MovieItem>) : Parcelable{}

data class MovieListResponse(var page: Int, val results : List<MovieItem>)

var loadingItem = object : ViewType{
    override fun getViewType(): Int {
        return AdapterType.LOADING
    }
}

data class ResponseData(val documents : List<ImageData>, val meta : String)

@Parcelize
data class ImageData(var image_url : String) : Parcelable

