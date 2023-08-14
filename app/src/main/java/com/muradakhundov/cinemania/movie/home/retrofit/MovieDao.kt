package com.muradakhundov.cinemania.movie.home.retrofit

import com.muradakhundov.cinemania.movie.home.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDao {

    @GET("movie/popular")
    fun getPopularMovie(
        @Query("api_key") api_key : String,
        @Query("page") page : Int = 1) : Call<MovieResponse>



}