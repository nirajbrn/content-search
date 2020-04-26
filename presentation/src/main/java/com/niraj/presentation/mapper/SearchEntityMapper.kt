package com.niraj.presentation.mapper

import com.niraj.domain.entities.MovieEntity
import com.niraj.presentation.model.Movie
import javax.inject.Inject

class SearchEntityMapper @Inject constructor() : Mapper<MovieEntity, Movie> {

    override fun from(e: Movie): MovieEntity {
        return MovieEntity(
            title = e.title,
            year = e.year,
            imdbId = e.imdbId,
            type = e.type,
            posterUrl = e.posterUrl
        )
    }

    override fun to(t: MovieEntity): Movie {
        return Movie(
            title = t.title,
            year = t.year,
            imdbId = t.imdbId,
            type = t.type,
            posterUrl = t.posterUrl
        )
    }
}