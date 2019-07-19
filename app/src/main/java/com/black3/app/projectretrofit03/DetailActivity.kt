package com.black3.app.projectretrofit03

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie_details.*

class DetailActivity : AppCompatActivity() {
    
    val PATH_URL_IMAGE= "https://image.tmdb.org/t/p/w500/"
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_movie_details)

        ShowDetailsMovies()
    }

    private fun ShowDetailsMovies()  {
        val bundle = intent.extras

        val popularity = bundle.get("popularity")
        textViewPopularity.text = popularity.toString()

        val votecount = bundle.get("votecount")
        textViewVote_count.text = votecount.toString()

        val posterpath = bundle.get("posterpath")
        Picasso.get().load(PATH_URL_IMAGE +"$posterpath").into(ivMovieDetails)

        val overview = bundle.get("overview")
        textViewOverview.text = overview.toString()

        val title = bundle.get("title")
        textViewTitle.text = title.toString()
    }

}
