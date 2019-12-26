package com.black3.app.searchmovies

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.black3.app.searchmovies.api.TheMovieDbApi
import com.black3.app.searchmovies.model.MovieList
import com.black3.app.searchmovies.utilities.ConnectionReceiver
import com.black3.app.searchmovies.utilities.MyApplication
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), android.support.v7.widget.SearchView.OnQueryTextListener,
    ConnectionReceiver.ConnectionReceiverListener {
    
    
    companion object {
        
        const val INTERNET_PERMISSION_REQUEST_CODE = 1
    }
    
    
    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if (isConnected) {
            btnWifiOnOff.text = "On"
            btnWifiOnOff.setBackgroundResource(R.drawable.rounded_button_on)
        } else {
            Toast.makeText(this, "No estás conectado a la red", Toast.LENGTH_LONG).show()
            btnWifiOnOff.text = "Off"
            btnWifiOnOff.setBackgroundResource(R.drawable.rounded_button_off)
        }
    
    }
    
    val POPULAR = "popular"
    val TOPRATED = "top_rated"
    val UPCOMING = "upcoming"
    val BASEURL = "https://api.themoviedb.org/3/"
    
    override fun onQueryTextSubmit(orderByTitle: String): Boolean {
        searchTitle(orderByTitle)
        return true
    }
    
    override fun onQueryTextChange(textSearch: String?): Boolean {
        if (textSearch == "" && textViewSort.text == "The most popular")
            fillData(POPULAR)
        if (textSearch == "" && textViewSort.text == "Comming soon")
            fillData(UPCOMING)
        if (textSearch == "" && textViewSort.text == "The most voted")
            fillData(TOPRATED)
        
        return true
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        fillData(POPULAR)
        //isConnectedNetwork(this, btnWifiOnOff)
        searchMovies.setOnQueryTextListener(this)
        baseContext.registerReceiver(
            ConnectionReceiver(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        MyApplication.instance.setConnectionListener(this)
        
    }
    
    fun fillData(orderBy: String) {
        when (orderBy) {
            POPULAR -> textViewSort.text = "The most popular"
            UPCOMING -> textViewSort.text = "Comming soon"
            TOPRATED -> textViewSort.text = "The most voted"
        }
        
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
    
    fun searchTitle(orderBy: String) {
        
        //Executing Retrtofit
        val retrofit = Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        
        //Getting data with Retrofit
        val api = retrofit.create(TheMovieDbApi::class.java)
        api.getMoviesByTitle(orderBy).enqueue(object : Callback<MovieList> {
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
        R.id.about_us -> {
            
            //setup the alert builder
            val alertMessage = AlertDialog.Builder(this, R.style.AlertDialogTheme)
            alertMessage.setTitle("Information about App and Developer")
            alertMessage.setMessage(
                """We are two brothers developers enthusiast and passionates for the technology,
                    |
                |Our emails are:
                |youngerblack@gmail.com
                |asesordeprogramacion@gmail.com
                |Medellin, Colombia
            """.trimMargin()
            )
            //add a button
            alertMessage.setPositiveButton("Ok", null)
            
            //create and show the alert dialog
            val dialog = alertMessage.create()
            dialog.show()
            
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
    
    fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
    
    
    //Confirm User Exit In Android App
    var doubleBackToExitOnce: Boolean = false
    
    override fun onBackPressed() {
        if (doubleBackToExitOnce) {
            super.onBackPressed()
            return
        }
        
        this.doubleBackToExitOnce = true
        
        //displays a toast message when user clicks exit button
        toast("please press again to exit ").show()
        
        //displays the toast message for a while
        Handler().postDelayed({
            run { doubleBackToExitOnce = false }
        }, 2000)
        
    }
    
        
    }
