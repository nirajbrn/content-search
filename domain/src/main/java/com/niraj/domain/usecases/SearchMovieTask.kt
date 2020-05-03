package com.niraj.domain.usecases

import com.niraj.domain.entities.MovieEntity
import com.niraj.domain.qualifiers.Background
import com.niraj.domain.qualifiers.Foreground
import com.niraj.domain.repository.SearchMovieRepository
import com.niraj.domain.usecases.base.ObservableUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import java.lang.IllegalArgumentException
import javax.inject.Inject

class SearchMovieTask @Inject constructor(
    private val searchMovieRepository: SearchMovieRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) : ObservableUseCase<List<MovieEntity>, SearchMovieTask.Params>(
    backgroundScheduler,
    foregroundScheduler
) {
    override fun generateObservable(input: Params?): Observable<List<MovieEntity>> {
        if (input == null) throw IllegalArgumentException("Search params can't be null")
        print("#niraj domain keyword: response ${input.searchIdentifier}")
        return searchMovieRepository.getMovieSearch(
            identifier = input.searchIdentifier,
            pageNumber = input.pageNumber
        )
    }

    data class Params(
        val searchIdentifier: String,
        val pageNumber: Int
    )

}