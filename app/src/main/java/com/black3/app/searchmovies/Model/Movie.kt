package com.black3.app.searchmovies.Model
import java.util.*

data class Movie (var vote_count : Int,
                  var id : Int,
                  var title : String,
                  var popularity : Double,
                  var overview : String,
                  var poster_path : String,
                  var adult : Boolean,
                  var release_date : Date,
                  var vote_average : Float
                  ){
}