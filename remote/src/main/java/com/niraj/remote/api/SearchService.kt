package com.niraj.remote.api

import com.niraj.remote.model.ResponseWrapper
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("/")
    fun getSearchMovieData(@Query("s") movieName: String, @Query("apikey") apiKey: String):
            Observable<ResponseWrapper>
}