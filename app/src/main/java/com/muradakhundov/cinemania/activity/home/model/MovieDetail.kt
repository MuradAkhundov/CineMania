package com.muradakhundov.cinemania.activity.home.model

data class MovieDetail(
    val id: Int,
    val original_title : String,
    val backdrop_path : String,
    val poster_path : String,
    val release_date : String,
    val genres : List<Genre>,
    val overview : String,
    val runtime : Int
) {
}