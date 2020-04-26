package com.niraj.domain.entities

data class MovieEntity(
    val title: String,
    val year: String,
    val imdbId: String,
    val type: String,
    val posterUrl: String
)