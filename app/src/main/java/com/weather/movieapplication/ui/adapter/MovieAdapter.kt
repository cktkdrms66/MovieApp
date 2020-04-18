package com.weather.movieapplication.ui.adapter

import android.util.SparseArray
import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.weather.movieapplication.data.MovieItem
import com.weather.movieapplication.data.loadingItem

class MovieAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    lateinit var items : ObservableArrayList<ViewType>
    private var delegateAdapter : SparseArrayCompat<ItemAdapter> = SparseArrayCompat()

    init {
        delegateAdapter.put(AdapterType.MOVIE, MovieItemAdapter())
        delegateAdapter.put(AdapterType.LOADING, LoadingItemAdapter())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        println(viewType)
        return delegateAdapter.get(viewType)!!.onCreateViewHolder(parent)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapter.get(items.get(position).getViewType())!!.onBindViewHolder(holder, items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addMovieList(movieList : List<MovieItem>){
        val initPosition = items.size - 1
        items.removeAt(initPosition)
        notifyItemRemoved(initPosition)

        items.addAll(movieList)
        items.add(loadingItem)
        notifyItemRangeChanged(initPosition, items.size-1)
    }

    fun clearAndAddMovieList(movieList: List<MovieItem>){
        items.clear()

        items.addAll(movieList)
        items.add(loadingItem)
    }

    fun getMovieList() : List<MovieItem> = items.filter { it.getViewType() == AdapterType.MOVIE }
        .map { it as MovieItem }


    override fun getItemViewType(position: Int): Int {
        return items.get(position).getViewType()
    }

}