package com.muradakhundov.cinemania.movie.home.data

import com.muradakhundov.cinemania.movie.home.model.Genre
import com.muradakhundov.cinemania.movie.home.model.GenreResponse
import com.muradakhundov.cinemania.movie.home.model.MovieResponse
import com.muradakhundov.cinemania.movie.home.retrofit.MovieDao
import retrofit2.Call

class MovieDataSource(val dao : MovieDao) {

    fun getPopularMovie(
       api_key : String,
       page : Int) : Call<MovieResponse> = dao.getPopularMovie(api_key,page)

    fun getUpcomingMovie(
        api_key: String,
        page: Int
    ) : Call<MovieResponse> = dao.getUpComingMovie(api_key,page)

    fun getTopRatedMovie(
        api_key: String,
        page: Int
    ) : Call<MovieResponse> = dao.getTopRatedMovie(api_key,page)

    fun getNowPlayingMovie(
        api_key: String,
        page: Int
    ) : Call<MovieResponse> = dao.getNowPlayingMovie(api_key,page)


    fun getAllGenre(
        api_key: String
    ) : Call<GenreResponse> = dao.getAllGenre(api_key)
}