package com.weather.movieapplication.data

import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RestApi{
    private val movieService : MovieService
    private val service : RetrofitService
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        movieService = retrofit.create(MovieService::class.java)

        val retrofit2 = Retrofit.Builder()
            .baseUrl(BASE_URL2)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        service = retrofit.create(RetrofitService::class.java)
    }

    fun getMovieListRetrofit(param : Map<String, String>) : Call<MovieListResponse>{
        return movieService.getTop(param)
    }


}