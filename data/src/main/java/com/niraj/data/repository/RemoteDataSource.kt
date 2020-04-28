package com.niraj.data.repository

import com.niraj.data.model.MovieData
import io.reactivex.Observable

interface RemoteDataSource {
    fun getSearchData(movieName: String, pageNumber: Int): Observable<List<MovieData>>
}