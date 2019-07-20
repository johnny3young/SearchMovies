package com.black3.app.projectretrofit03

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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

class MainActivity : AppCompatActivity(), android.support.v7.widget.SearchView.OnQueryTextListener {
    
    val POPULAR = "popular"
    val TOPRATED = "top_rated"
    val UPCOMING = "upcoming"
    val BASEURL = "https://api.themoviedb.org/3/"
    
    
    override fun onQueryTextSubmit(orderByTitle: String): Boolean {
        
        return true
    }
    
    override fun onQueryTextChange(p0: String?): Boolean {
        
        return true
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        fillData(POPULAR)
        searchMovies.setOnQueryTextListener(this)
        
    }
    
    fun fillData(orderBy: String) {
        
        //Executing Retrtofit
        val retrofit = Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        
        //Getting data with Retrofit
        val api = retrofit.create(TheMovieDbApi::class.java)
        api.getMoviesBy(orderBy).enqueue(object : Callback<MovieList> {
            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                var movies = response.body()?.movies!!
                
                //Executing Recyclerview
                recyclerViewMovies.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = AdapterMovie(movies)
                }
            }
            
            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                Log.e("Message onFailure", "Inside method onFailure")
            }
        })
    }
    
    //This method is implemented to add elements to Toolbar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //Inflate menu items to use Toolbar
        val inflater = menuInflater
        inflater.inflate(R.menu.options_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }
    
    // actions on click menu items
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_Popular -> {
            showMessage("Showing the most popular")
            fillData(POPULAR)
            true
        }
        R.id.action_Top_Rated -> {
            showMessage("Showing the top rated")
            fillData(TOPRATED)
            true
        }
        R.id.action_Upcoming -> {
            showMessage("Showing the most upcoming")
            fillData(UPCOMING)
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
    
    fun showMessage (msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
 }