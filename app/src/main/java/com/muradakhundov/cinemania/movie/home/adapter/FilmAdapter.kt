package com.muradakhundov.cinemania.movie.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.makeramen.roundedimageview.RoundedImageView
import com.muradakhundov.cinemania.databinding.CardFilmBinding
import com.muradakhundov.cinemania.movie.home.model.Movie

class FilmAdapter(val mContext : Context , val filmList : List<Movie>) : RecyclerView.Adapter<FilmAdapter.FilmDesignHolder>(){

    inner class FilmDesignHolder(val binding : CardFilmBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmDesignHolder {
        val binding = CardFilmBinding.inflate(LayoutInflater.from(mContext))
        return FilmDesignHolder(binding)
    }

    override fun getItemCount(): Int {
        return filmList.size
    }

    override fun onBindViewHolder(holder: FilmDesignHolder, position: Int) {
        val b = holder.binding
        val list = filmList.get(position)
        b.posterTitle.text = list.original_title
        setImageWithGlide(list.backdrop_path,b.posterFilm)


    }

    private fun setImageWithGlide(imageName: String, imageView: ImageView) {
        val url = "https://image.tmdb.org/t/p/w500/$imageName"
        Glide.with(mContext).load(url).override(300, 150).into(imageView)
    }
}