package com.niraj.data.mapper

import com.niraj.data.model.MovieData
import com.niraj.domain.entities.MovieEntity
import javax.inject.Inject

class SearchDomainDataMapper @Inject constructor() : Mapper<MovieEntity, MovieData> {
    override fun from(e: MovieData): MovieEntity {
        return MovieEntity(
            title = e.title,
            year = e.year,
            imdbId = e.imdbId,
            type = e.type,
            posterUrl = e.posterUrl
        )
    }

    override fun to(t: MovieEntity): MovieData {
        return MovieData(
            title = t.title,
            year = t.year,
            imdbId = t.imdbId,
            type = t.type,
            posterUrl = t.posterUrl
        )
    }
}