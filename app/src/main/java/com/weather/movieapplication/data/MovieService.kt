package com.weather.movieapplication.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.QueryMap


const val API_KEY = "ae32646ae5ad92feff82aed8f4307070"
const val BASE_URL = "https://api.themoviedb.org/3/"

interface MovieService{

    @GET("discover/movie")
    fun getTop(@QueryMap par : Map<String, String>): Call<MovieListResponse>
}


const val API_KEY2 = "ed25cdd7c21ad5233472760ad0506c88"
const val BASE_URL2 = "https://dapi.kakao.com"

interface RetrofitService{

    @GET("/v2/search/image")
    fun searchImage(@Header("Authorization") auth:String,
                    @Query("query") query:String,
                    @Query("sort") sort:String,
                    @Query("page") page:Int,
                    @Query("size") size:Int): Call<ResponseData>

}