package com.muradakhundov.cinemania.movie.home.repo

import com.muradakhundov.cinemania.movie.home.data.MovieDataSource
import com.muradakhundov.cinemania.movie.home.model.Genre
import com.muradakhundov.cinemania.movie.home.model.GenreResponse
import com.muradakhundov.cinemania.movie.home.model.MovieResponse
import retrofit2.Call

class MovieRepository(val dataSource: MovieDataSource) {

    fun getPopularMovie(
        api_key: String,
        page: Int
    ): Call<MovieResponse> = dataSource.getPopularMovie(api_key, page)

    fun getUpcomingMovie(
        api_key: String,
        page: Int
    ): Call<MovieResponse> = dataSource.getUpcomingMovie(api_key, page)

    fun getTopRatedMovie(
        api_key: String,
        page: Int
    ): Call<MovieResponse> = dataSource.getTopRatedMovie(api_key, page)

    fun getNowPlayingMovie(
        api_key: String,
        page: Int
    ): Call<MovieResponse> = dataSource.getNowPlayingMovie(api_key, page)
    fun getAllGenre(
        api_key: String
    ): Call<GenreResponse> = dataSource.getAllGenre(api_key)
}