package com.niraj.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.niraj.domain.usecases.SearchMovieTask
import com.niraj.presentation.mapper.SearchEntityMapper
import com.niraj.presentation.model.Movie
import com.niraj.presentation.model.Resource
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.functions.Function
import javax.inject.Inject

class SearchMovieVM @Inject internal constructor(
    private val searchMapper: SearchEntityMapper,
    private val searchMovieTask: SearchMovieTask
) : ViewModel() {

    private fun searchRequest(movieName: String, pageNumber: Int) =
        SearchMovieTask.Params(
            movieName, pageNumber
        )

    fun searchMovies(movieName: String, pageNumber: Int): LiveData<Resource<List<Movie>>> {
        return searchMovieTask.buildUseCase(searchRequest(movieName, pageNumber))
            .map { movieEntities ->
                movieEntities.map {
                    searchMapper.to(it)
                }
            }
            .map { Resource.success(it) }
            .startWith(Resource.loading())
            .onErrorResumeNext(
                Function {
                    Observable.just(Resource.error(it.localizedMessage))
                }
            ).toFlowable(BackpressureStrategy.LATEST)
            .toLiveData()
    }
}