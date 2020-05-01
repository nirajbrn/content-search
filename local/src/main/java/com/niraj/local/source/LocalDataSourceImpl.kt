package com.niraj.local.source

import com.niraj.data.mapper.SearchDomainDataMapper
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
        return movieDAO.getMovieDetails(movieName, pageNumber).map { moviesLocal ->
            moviesLocal.map { movieLocal ->
                movieDataLocalMapper.from(movieLocal)
            }
        }
    }

    override fun saveMovieResult(movieName: String, moviesData: List<MovieData>) {
        movieDAO.saveMovieDetails(
            movieName,
            moviesData.map { movieData -> movieDataLocalMapper.to(movieName, movieData) })
    }
}