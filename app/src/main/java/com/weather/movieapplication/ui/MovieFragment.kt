package com.weather.movieapplication.ui

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.IntegerRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.squareup.picasso.Picasso
import com.weather.movieapplication.MainActivity
import com.weather.movieapplication.R
import com.weather.movieapplication.data.MovieItem
import com.weather.movieapplication.data.MovieList
import com.weather.movieapplication.data.loadingItem
import com.weather.movieapplication.databinding.FragmentRecyclerBinding
import com.weather.movieapplication.ui.adapter.MovieAdapter
import com.weather.movieapplication.ui.adapter.ViewType
import com.weather.movieapplication.ui.scroll.ScrollListener
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.image
import java.net.URI
import java.net.URL

class MovieFragment : RxBaseFragment(){

    private lateinit var binding : FragmentRecyclerBinding
    private val movieManager by lazy{ MovieManager()}
    var movieList = ObservableArrayList<ViewType>()
    var page : Int? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        page = 1

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recycler, container, false)
        binding.fragment = this

        binding.recyclerViewMovie.setHasFixedSize(true)
        binding.recyclerViewMovie.layoutManager = GridLayoutManager(activity, 3)
        binding.recyclerViewMovie.adapter = MovieAdapter()
        binding.recyclerViewMovie.clearOnScrollListeners()
        binding.recyclerViewMovie.addOnScrollListener(ScrollListener({requestMovie()},
            binding.recyclerViewMovie.layoutManager as GridLayoutManager))
        binding.movies = movieList
        movieList.add(loadingItem)

        requestMovie()

        return binding.root
    }

    fun requestMovie(){
        val subscription = movieManager.getMovieList(page.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({movies ->
                (binding.recyclerViewMovie.adapter as MovieAdapter).addMovieList(movies)
                page = page?.plus(1)
            },{ Log.d("tag","asdasd")})

       subscriptions.add(subscription)
    }

}