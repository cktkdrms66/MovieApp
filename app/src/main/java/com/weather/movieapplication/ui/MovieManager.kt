package com.weather.movieapplication.ui

import androidx.databinding.ObservableArrayList
import com.weather.movieapplication.data.*
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MovieManager(private val restApi: RestApi = RestApi()){

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
        service = retrofit2.create(RetrofitService::class.java)
    }

    fun getMovieList(page: String): Observable<List<MovieItem>>{
        return Observable.create{subscriber->
            val param = mapOf<String, String>(
                "page" to page,
                "api_key" to API_KEY,
                "sort_by" to "popularity.desc",
                "language" to "ko"
            )
            val call = movieService.getTop(param)
            val response = call.execute()

            if(response.isSuccessful){
                val movieListResults = response.body()?.results?.map {

                    MovieItem(it.title, it.poster_path)
                }

                if(movieListResults != null){
                    subscriber.onNext(movieListResults)
                }
                subscriber.onComplete()
            }else{
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
    fun getImage(name : String, page : Int) : Observable<List<ImageData>>{

        return Observable.create {
            val call = service.searchImage("KakaoAK $API_KEY2", name, "accuracy", page, 1)
            val response = call.execute()
            println(response.toString())

            if(response.isSuccessful){
                println("success")
                val result = response.body()?.documents?.map {
                    println(it.image_url)
                    ImageData(it.image_url)
                }

                if(result != null){
                    it.onNext(result)
                }
                it.onComplete()

            }else{
                it.onError(Throwable(response.message()))
            }
        }

    }
}