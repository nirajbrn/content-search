package com.niraj.remote.model

import com.google.gson.annotations.SerializedName

data class MoviesNetwork(
    @SerializedName("title") val title: String,
    @SerializedName("year") val year: String,
    @SerializedName("imdbId") val imdbId: String,
    @SerializedName("type") val type: String,
    @SerializedName("posterUrl") val posterUrl: String
)