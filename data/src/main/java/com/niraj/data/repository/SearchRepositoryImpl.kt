package com.niraj.data.repository

import com.niraj.data.mapper.Mapper
import com.niraj.data.model.MovieData
import com.niraj.domain.entities.MovieEntity
import com.niraj.domain.repository.SearchMovieRepository
import io.reactivex.Observable
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchMovieMapper: Mapper<MovieEntity, MovieData>,
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : SearchMovieRepository {

    override fun getMovieSearch(
        identifier: String,
        pageNumber: Int
    ): Observable<List<MovieEntity>> {
        val searchMovieObservable = localDataSource.getSearchData(identifier, pageNumber)
            .map { movies -> movies.map {  searchMovieMapper.from(it) } }
        return remoteDataSource.getSearchData(identifier,pageNumber)
            .map {movies ->
                localDataSource.saveMovieResult(identifier, movies)
                movies.map { searchMovieMapper.from(it) }
            }.onErrorResumeNext(Observable.empty())
            .concatWith(searchMovieObservable)
    }
}