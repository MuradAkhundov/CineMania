package com.muradakhundov.cinemania.movie.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muradakhundov.cinemania.databinding.CategoryListBinding
import com.muradakhundov.cinemania.movie.home.model.Genre

class GenreAdapter(val mContext : Context , val genreList : List<Genre>) : RecyclerView.Adapter<GenreAdapter.GenreDesignHolder>() {


    inner class GenreDesignHolder(val binding : CategoryListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreDesignHolder {
        val binding = CategoryListBinding.inflate(LayoutInflater.from(mContext))
        return GenreDesignHolder(binding)
    }

    override fun getItemCount(): Int {
        return genreList.size
    }

    override fun onBindViewHolder(holder: GenreDesignHolder, position: Int) {
        val b = holder.binding
        val list = genreList.get(position)
        b.genrebtn.text = list.name
    }
}