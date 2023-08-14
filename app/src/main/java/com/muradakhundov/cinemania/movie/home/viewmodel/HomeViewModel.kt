package com.muradakhundov.cinemania.movie.home.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muradakhundov.cinemania.movie.home.model.Genre
import com.muradakhundov.cinemania.movie.home.model.GenreResponse
import com.muradakhundov.cinemania.movie.home.model.MovieResponse
import com.muradakhundov.cinemania.movie.home.repo.MovieRepository
import com.muradakhundov.cinemania.movie.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(var repo : MovieRepository) : ViewModel() {


    val popularMovie = MutableLiveData<MovieResponse>()
    val upcomingMovie = MutableLiveData<MovieResponse>()
    val nowplayingMovie = MutableLiveData<MovieResponse>()
    val topratedMovie = MutableLiveData<MovieResponse>()
    val genreList = MutableLiveData<GenreResponse>(GenreResponse(emptyList()))

    init {
        getPopularMovie()
        getUpcomingMovie()
        getNowPlayingMovie()
        getTopRatedMovie()
//        getAllGenre()
    }

    fun getPopularMovie() {
        val response = repo.getPopularMovie(Constants.API_KEY,1)
        response.enqueue(object : Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
              if (response.isSuccessful){
                  popularMovie.value = response.body()

              }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
            }

        })
    }

    fun getUpcomingMovie(){
        val response = repo.getUpcomingMovie(Constants.API_KEY,1)
        response.enqueue(object  : Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful){
                    upcomingMovie.value = response.body()

                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
            }

        })
    }

    fun getTopRatedMovie(){
        val response = repo.getTopRatedMovie(Constants.API_KEY,1)
        response.enqueue(object : Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful){
                    topratedMovie.value = response.body()

                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
            }

        })

    }

    fun getNowPlayingMovie(){
        val response = repo.getNowPlayingMovie(Constants.API_KEY,1)
        response.enqueue(object : Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful){
                    nowplayingMovie.value = response.body()

                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
            }

        })

    }

    fun getAllGenre(){
        val response = repo.getAllGenre(Constants.API_KEY)
        response.enqueue(object : Callback<GenreResponse>{

            override fun onResponse(call: Call<GenreResponse>, response: Response<GenreResponse>) {
                if (response.isSuccessful){
                    genreList.value = response.body()
                    Log.e("genre","successful")

                }
            }

            override fun onFailure(call: Call<GenreResponse>, t: Throwable) {
                Log.e("tag",t.toString())

            }

        })
    }
}