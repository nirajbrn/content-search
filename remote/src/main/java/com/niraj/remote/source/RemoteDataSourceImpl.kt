package com.niraj.remote.source

import com.niraj.data.model.MovieData
import com.niraj.data.repository.RemoteDataSource
import com.niraj.remote.api.SearchService
import com.niraj.remote.mapper.Mapper
import com.niraj.remote.model.MoviesNetwork
import io.reactivex.Observable
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val movieDataMapper: Mapper<MovieData, MoviesNetwork>,
    private val searchService: SearchService
) : RemoteDataSource {

    override fun getSearchData(movieName: String, pageNumber: Int): Observable<List<MovieData>> {
        return searchService.getSearchMovieData(movieName)
            .map { response ->
                print("#niraj Remote keyword ${movieName}")
                response.movies.map { moviesNetwork: MoviesNetwork ->
                    movieDataMapper.from(
                        moviesNetwork
                    )
                }
            }
    }
}