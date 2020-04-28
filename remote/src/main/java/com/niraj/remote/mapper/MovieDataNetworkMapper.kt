package com.niraj.remote.mapper

import com.niraj.data.model.MovieData
import com.niraj.remote.model.MoviesNetwork


class MovieDataNetworkMapper : Mapper<MovieData, MoviesNetwork> {
    override fun from(e: MoviesNetwork): MovieData {
        return MovieData(
            title = e.title,
            year = e.year,
            imdbId = e.imdbId,
            type = e.type,
            posterUrl = e.posterUrl
        )
    }

    override fun to(t: MovieData): MoviesNetwork {
        return MoviesNetwork(
            title = t.title,
            year = t.year,
            imdbId = t.imdbId,
            type = t.type,
            posterUrl = t.posterUrl
        )
    }
}