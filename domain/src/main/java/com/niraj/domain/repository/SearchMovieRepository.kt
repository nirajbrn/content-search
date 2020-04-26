package com.niraj.domain.repository

import com.niraj.domain.entities.MovieEntity
import io.reactivex.Observable

interface SearchMovieRepository {
    fun getMovieSearch(identifier: String, pageNumber: Int) : Observable<List<MovieEntity>>
}