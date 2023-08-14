package com.muradakhundov.cinemania.movie.home.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GenreResponse(
    @SerializedName("results")
    @Expose
    val genres : List<Genre>
) : Serializable