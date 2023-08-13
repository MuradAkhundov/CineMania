package com.muradakhundov.cinemania.activity.home.retrofit

import com.muradakhundov.cinemania.activity.util.Constants

class ApiUtils {
    companion object {
        fun getDao() : MovieDao{
            return RetrofitClient.getClient(Constants.BASE_URL).create(MovieDao::class.java)
        }
    }
}