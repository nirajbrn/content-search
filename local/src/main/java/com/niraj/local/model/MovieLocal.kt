package com.niraj.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_details")
data class MovieLocal(
    @PrimaryKey @ColumnInfo(name = "search_key") val keyword: String,
    @ColumnInfo(name = "movie_title") val title: String,
    @ColumnInfo(name = "movie_year") val year: String,
    @ColumnInfo(name = "movie_imdb_id") val imdbId: String,
    @ColumnInfo(name = "movie_type") val type: String,
    @ColumnInfo(name = "poster_url") val posterUrl: String
)