package com.niraj.local.database

import androidx.room.Dao
import com.niraj.local.model.MovieLocal
import io.reactivex.Observable

@Dao
interface MovieDAO {
    fun getMovieDetails(keyword: String, pageNumber: Int) : Observable<List<MovieLocal>>
    fun saveMovieDetails(keyword: String, movies: List<MovieLocal>)
}