package com.muradakhundov.cinemania.activity.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muradakhundov.cinemania.activity.home.model.Movie
import com.muradakhundov.cinemania.databinding.PosterItemsBinding

class CarouselAdapter(
    var mContext : Context,
    var movieList : List<Movie>
) : RecyclerView.Adapter<CarouselAdapter.CarouselDesignHolder>() {


    inner class CarouselDesignHolder(val binding : PosterItemsBinding ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselDesignHolder {
        val binding = PosterItemsBinding.inflate(LayoutInflater.from(mContext))
        return CarouselDesignHolder(binding)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: CarouselDesignHolder, position: Int) {
        val b = holder.binding
        val list = movieList.get(position)
        b.movieTitle.text = list.original_title
        b.movieDate.text = list.release_date
        b.moviePoster

    }


}