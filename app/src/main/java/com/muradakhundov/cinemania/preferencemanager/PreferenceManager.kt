package com.muradakhundov.cinemania.preferencemanager

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(val mContext : Context){
    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = mContext.getSharedPreferences("CineMania", Context.MODE_PRIVATE)
    }

    public fun getEmail(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    public fun setEmail(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    public fun setEmailBoolean(key: String , value: Boolean){
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    public fun getEmailBoolean(key: String) : Boolean{
        return sharedPreferences.getBoolean(key, false)
    }

    public fun getUsername(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    public fun setUsername(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun clear(){
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }


}