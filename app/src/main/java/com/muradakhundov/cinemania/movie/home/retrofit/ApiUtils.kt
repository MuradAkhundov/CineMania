package com.muradakhundov.cinemania.movie.home.retrofit

import com.muradakhundov.cinemania.movie.util.Constants

class ApiUtils {
    companion object {
        fun getDao() : MovieDao{
            return RetrofitClient.getClient(Constants.BASE_URL).create(MovieDao::class.java)
        }
    }
}