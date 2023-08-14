package com.muradakhundov.cinemania.movie.auth.activity.entity

import java.io.Serializable

data class Users(
    var name : String = "",
    var email : String = "",
    var uid : String = "",
    var image : String =""
) : Serializable