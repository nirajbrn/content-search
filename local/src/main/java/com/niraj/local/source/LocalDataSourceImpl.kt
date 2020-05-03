package com.niraj.local.source

import com.niraj.data.model.MovieData
import com.niraj.data.repository.LocalDataSource
import com.niraj.local.database.MovieDAO
import com.niraj.local.mapper.MovieDataLocalMapper
import io.reactivex.Observable
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val movieDataLocalMapper: MovieDataLocalMapper,
    private val movieDAO: MovieDAO
) : LocalDataSource {
    override fun getSearchData(movieName: String, pageNumber: Int): Observable<List<MovieData>> {
        print("#niraj Local keyword ${movieName}")
        return movieDAO.getMovieDetails(movieName).map { moviesLocal ->
            moviesLocal.map { movieLocal ->
                movieDataLocalMapper.from(movieLocal)
            }
        }
    }

    override fun saveMovieResult(movieName: String, moviesData: List<MovieData>) {
        movieDAO.saveMovieDetails(
            moviesData.map { movieData -> movieDataLocalMapper.to(movieName, movieData) })
    }
}