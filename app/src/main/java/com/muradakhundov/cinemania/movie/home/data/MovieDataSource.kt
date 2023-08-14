package com.muradakhundov.cinemania.movie.home.data

import com.muradakhundov.cinemania.movie.home.model.MovieResponse
import com.muradakhundov.cinemania.movie.home.retrofit.MovieDao
import retrofit2.Call

class MovieDataSource(val dao : MovieDao) {

    fun getPopularMovie(
       api_key : String,
       page : Int) : Call<MovieResponse> = dao.getPopularMovie(api_key,page)
}