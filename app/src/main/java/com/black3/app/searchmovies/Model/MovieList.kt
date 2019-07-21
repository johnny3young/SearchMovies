package com.black3.app.searchmovies.Model

import com.google.gson.annotations.SerializedName

class MovieList {
    @SerializedName("results")
    var movies: ArrayList<Movie>? = null
}
