package com.muradakhundov.cinemania.movie.home.repo

import com.muradakhundov.cinemania.movie.home.data.MovieDataSource
import com.muradakhundov.cinemania.movie.home.model.MovieResponse
import retrofit2.Call

class MovieRepository(val dataSource : MovieDataSource) {

    fun getPopularMovie(
        api_key : String,
        page : Int) : Call<MovieResponse> = dataSource.getPopularMovie(api_key,page)
}