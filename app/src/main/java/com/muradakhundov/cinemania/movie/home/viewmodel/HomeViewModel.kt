package com.muradakhundov.cinemania.movie.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    init {
        getPopularMovie()
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
}