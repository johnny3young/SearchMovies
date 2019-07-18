package com.black3.app.projectretrofit03

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_imagen.*
import kotlinx.android.synthetic.main.item_movie_details.*

class ImagenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imagen)

        mostrarImagen()
    }

    private fun mostrarImagen() {
        val bundle = intent.extras

        val posterpath = bundle.get("posterpath")
        Picasso.get().load("https://image.tmdb.org/t/p/w500/$posterpath").into(ivImagen)
    }
}
