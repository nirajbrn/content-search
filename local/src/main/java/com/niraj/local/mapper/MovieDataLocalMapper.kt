package com.niraj.local.mapper

import com.niraj.data.model.MovieData
import com.niraj.local.model.MovieLocal

class MovieDataLocalMapper : Mapper<MovieData, MovieLocal, String> {
    override fun from(e: MovieLocal): MovieData {
        return MovieData(
            title = e.title,
            year = e.year,
            imdbId = e.imdbId,
            type = e.type,
            posterUrl = e.posterUrl
        )
    }

    override fun to(key: String, t: MovieData): MovieLocal {
        return MovieLocal(
            keyword = "",
            title = t.title,
            year = t.year,
            imdbId = t.imdbId,
            type = t.type,
            posterUrl = t.posterUrl
        )
    }
}