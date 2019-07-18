package com.black3.app.projectretrofit03

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.black3.app.projectretrofit03.Interface.TheMovieDbApi
import com.black3.app.projectretrofit03.Model.MovieList
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TopRatedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        //Ejecutando Retrtofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        //Trayendo la data con Retrofit
        val api = retrofit.create(TheMovieDbApi::class.java)
        api.getMoviesByTopRated().enqueue(object : Callback<MovieList> {
            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                var moviesList = response.body()
                var movies = moviesList?.movies!!

                //Ejecutar el Recyclerview
                recyclerViewMovies.apply {
                    layoutManager = LinearLayoutManager(this@TopRatedActivity)
                    adapter = AdapterMovie(movies)
                }
            }
            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                Log.e("Johnny", "Probando el onFailure")
            }
        })

    }
    //Se implementa este método para añadir elementos al Toolbar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //Se inflan los elementos del menú para usar Toolbar
        val inflater = menuInflater
        inflater.inflate(R.menu.options_toolbar,menu)
        return super.onCreateOptionsMenu(menu)
    }
    // actions on click menu items
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_Popular -> {
            msgShow("Mostrando las más populares")
            val myIntent = Intent (this,PopularActivity::class.java)
            startActivity(myIntent)
            true
        }
        R.id.action_Top_Rated -> {
            msgShow("Mostrando las más valoradas")
            val myIntent = Intent (this,TopRatedActivity::class.java)
            startActivity(myIntent)
            true
        }
        R.id.action_Upcoming -> {
            msgShow("Mostrando los próximos estrenos")
            val myIntent = Intent (this,UpcomingActivity::class.java)
            startActivity(myIntent)
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    fun msgShow(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

}