package com.black3.app.searchmovies.model

import com.google.gson.annotations.SerializedName

class MovieList {
    @SerializedName("results")
    var movies: ArrayList<Movie>? = null
}
