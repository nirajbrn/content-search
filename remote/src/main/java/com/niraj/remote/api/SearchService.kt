package com.niraj.remote.api

import com.niraj.remote.model.ResponseWrapper
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("/?apikey=c4c6ccd7")
    fun getSearchMovieData(@Query("s") movieName: String):
            Observable<ResponseWrapper>
}