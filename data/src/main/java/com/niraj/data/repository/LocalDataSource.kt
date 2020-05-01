package com.niraj.data.repository

import com.niraj.data.model.MovieData
import io.reactivex.Observable

interface LocalDataSource {
    fun getSearchData(movieName: String, pageNumber: Int): Observable<List<MovieData>>
    fun saveMovieResult(movieName: String, moviesData: List<MovieData>)
}