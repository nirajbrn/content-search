package com.niraj.remote.model

import com.google.gson.annotations.SerializedName

data class MoviesNetwork(
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("imdbID") val imdbId: String,
    @SerializedName("Type") val type: String,
    @SerializedName("Poster") val posterUrl: String
)