package com.black3.app.projectretrofit03

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log.e
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.Transformation
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

        holder.vote_average.text = movies[position].vote_average.toString()
        holder.title.text = movies[position].title
        val fechaFormat  = SimpleDateFormat("yyyy-MM-dd")
        holder.release_date.text = fechaFormat.format(movies[position].release_date)

        Picasso.get().load("https://image.tmdb.org/t/p/w500/${movies[position].poster_path}").transform(CircleTransformation()).into(holder.imageView)

        holder.itemView.ivMovie.setOnClickListener{
            val z = Intent(holder.itemView.context,ImagenActivity::class.java)
            z.putExtra("posterpath",movies[position].poster_path)
           holder.imageView.animate().scaleX(1.0f).scaleY(1.0f).duration = 2000
            holder.itemView.context.startActivity(z)
        }

        //Efecto zoom al darle click a la imagen inicial
        /*holder.itemView.ivMovie.setOnClickListener{
            val zoomAnimation : Animation = AnimationUtils.loadAnimation(holder.itemView.context,R.anim.zoom)
            holder.imageView.startAnimation(zoomAnimation)
        }*/

        holder.itemView.setOnClickListener {

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
        val imageView = itemView.ivMovie
    }
}
