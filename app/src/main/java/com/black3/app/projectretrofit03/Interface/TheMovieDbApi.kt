package com.black3.app.projectretrofit03.Interface

import com.black3.app.projectretrofit03.Model.MovieList
import retrofit2.Call
import retrofit2.http.GET

interface TheMovieDbApi {

@GET("movie/popular?api_key=497db81ee7de37cce7137fcda59ed2d5")
fun getMoviesByPopularity() : Call<MovieList>

@GET("movie/top_rated?api_key=497db81ee7de37cce7137fcda59ed2d5")
fun getMoviesByTopRated() : Call<MovieList>

@GET("movie/upcoming?api_key=497db81ee7de37cce7137fcda59ed2d5")
fun getMoviesByUpcoming() : Call<MovieList>

}