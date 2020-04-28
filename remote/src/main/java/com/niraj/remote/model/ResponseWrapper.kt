package com.niraj.remote.model

import com.google.gson.annotations.SerializedName

class ResponseWrapper(
    @SerializedName("Search") val movies: List<MoviesNetwork>
)