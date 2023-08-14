package com.muradakhundov.cinemania.movie.home.retrofit

import com.muradakhundov.cinemania.movie.home.model.Genre
import com.muradakhundov.cinemania.movie.home.model.GenreResponse
import com.muradakhundov.cinemania.movie.home.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDao {

    @GET("movie/popular")
    fun getPopularMovie(
        @Query("api_key") api_key : String,
        @Query("page") page : Int = 1) : Call<MovieResponse>


    @GET("genre/movie/list")
    fun getAllGenre(
        @Query("api_key") api_key: String
    ) : Call<GenreResponse>



    @GET("movie/upcoming")
    fun getUpComingMovie(
        @Query("api_key") api_key: String,
        @Query("page") page: Int = 1): Call<MovieResponse>


    @GET("movie/top_rated")
    fun getTopRatedMovie(
        @Query("api_key") api_key: String,
        @Query("page") page: Int = 1): Call<MovieResponse>



    @GET("movie/now_playing")
    fun getNowPlayingMovie(
        @Query("api_key") api_key: String,
        @Query("page") page: Int = 1): Call<MovieResponse>

}