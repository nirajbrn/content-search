package com.niraj.contentsearch.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.niraj.contentsearch.R
import com.niraj.presentation.model.Movie
import kotlinx.android.synthetic.main.movie_item_layout.view.*

class MoviesAdapter() : RecyclerView.Adapter<MoviesAdapter.MoviesVH>() {

    private var movieList: MutableList<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesVH {
        return MoviesVH(
            LayoutInflater.from(parent.context).inflate(
                R.layout.movie_item_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: MoviesVH, position: Int) =
        holder.bind(movieList[position])

    fun populate(movies: List<Movie>) {
        movieList = ArrayList(movies)
        notifyDataSetChanged()
    }

    fun reset() {
        movieList = ArrayList()
    }

    inner class MoviesVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie) {
            itemView.name.text = movie.title
            itemView.year.text = movie.year
            Glide.with(itemView)
                .load(movie.posterUrl)
                .into(itemView.posterImv)
        }

    }
}