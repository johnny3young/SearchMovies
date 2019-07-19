package com.black3.app.projectretrofit03.Model

import com.google.gson.annotations.SerializedName

class MovieList {
    @SerializedName("results")
    var movies: ArrayList<Movie>? = null
}
