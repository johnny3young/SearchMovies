package com.black3.app.projectretrofit03.Interface

import com.black3.app.projectretrofit03.Model.MovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TheMovieDbApi {

@GET("movie/{orderby}?api_key=497db81ee7de37cce7137fcda59ed2d5")
fun getMoviesBy(@Path(value = "orderby", encoded = true) orderby : String) : Call<MovieList>


@GET("search/movie?api_key=497db81ee7de37cce7137fcda59ed2d5&{searchbytitle}")
fun getMoviesByTitle(@Path(value = "searchbytitle", encoded = true) searchbytitle : String) : Call<MovieList>

}