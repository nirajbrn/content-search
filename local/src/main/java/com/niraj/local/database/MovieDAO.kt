package com.niraj.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.niraj.local.model.MovieLocal
import io.reactivex.Observable

@Dao
interface MovieDAO {

    @Query("SELECT * FROM movie_details WHERE search_key = :keyword")
    fun getMovieDetails(keyword: String) : Observable<List<MovieLocal>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovieDetails(movies: List<MovieLocal>)
}