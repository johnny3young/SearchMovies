package com.black3.app.projectretrofit03

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log.e
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.black3.app.projectretrofit03.Model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.android.synthetic.main.item_movie.view.textViewTitle
import java.text.SimpleDateFormat

class AdapterMovie (val movies : MutableList<Movie>) : RecyclerView.Adapter <AdapterMovie.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(p0.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_movie, p0,false))
    }
    override fun getItemCount(): Int {
        return movies.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Puedo declarar movie = movies[position] para reducir código y quedaría
        //holder.votecount.text = movie.vote_count
        holder.vote_average.text = movies[position].vote_average.toString()
        holder.title.text = movies[position].title
        val fechaFormat  = SimpleDateFormat("yyyy-MM-dd")
        holder.release_date.text = fechaFormat.format(movies[position].release_date)

        if (movies[position].adult.toString() == "false"){
            holder.adult.text = "No"
        }else{
            holder.adult.text = "Si"
        }

        Picasso.get().load("https://image.tmdb.org/t/p/w500/${movies[position].poster_path}").into(holder.imageView)

        holder.itemView.setOnClickListener {
            e("setOncLicListener","Validando el onBindViewHolder con Click")
            val z = Intent(holder.itemView.context,DetailActivity::class.java)
            z.putExtra("popularity",movies[position].popularity.toString() )
            z.putExtra("votecount",movies[position].vote_count.toString())
            z.putExtra("posterpath",movies[position].poster_path)
            z.putExtra("overview", movies[position].overview)
            z.putExtra("title", movies[position].title)
            holder.itemView.context.startActivity(z)
        }
    }
    class ViewHolder (view : View) : RecyclerView.ViewHolder(view) {
        val vote_average = itemView.textViewVote_Average
        val title = itemView.textViewTitle
        val release_date = itemView.textViewRelease_Date
        val adult = itemView.textViewAdult
        val imageView = itemView.ivMovie
    }
}