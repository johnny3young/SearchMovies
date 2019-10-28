package com.black3.app.searchmovies.`interface`

import com.black3.app.searchmovies.model.MovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDbApi {

@GET("movie/{orderby}?api_key=497db81ee7de37cce7137fcda59ed2d5")
fun getMoviesBy(@Path(value = "orderby", encoded = true) orderby : String) : Call<MovieList>


@GET("search/movie?api_key=497db81ee7de37cce7137fcda59ed2d5")
fun getMoviesByTitle(@Query("query") searchbytitle : String) : Call<MovieList>

}