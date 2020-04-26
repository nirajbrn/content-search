package com.niraj.domain.usecases

import com.niraj.domain.repository.SearchMovieRepository
import com.niraj.domain.utils.TestDataGenerator
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class SearchMoviesTest {

    private lateinit var searchMovieTask: SearchMovieTask

    @Mock
    lateinit var searchMovieRepository: SearchMovieRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        searchMovieTask = SearchMovieTask(
            searchMovieRepository,
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )
    }

    @Test
    fun test_searchMovieTask_success() {
        val identifier = "Jungle"
        val pageNumber = 1
        val movieList = TestDataGenerator.generateSearchResult()

        Mockito.`when`(searchMovieRepository.getMovieSearch(identifier, pageNumber))
            .thenReturn(Observable.just(movieList))

        val testObserver = searchMovieTask.buildUseCase(
            SearchMovieTask.Params(
                identifier,
                pageNumber
            )
        ).test()

        testObserver
            .assertSubscribed()
            .assertValue { it.containsAll(movieList) }

    }

    @Test
    fun test_searchMovieTask_error() {
        val identifier = "Jungle"
        val pageNumber = 1
        val errorMessage = "Movie not found!"
        val movieList = TestDataGenerator.generateSearchResult()

        Mockito.`when`(searchMovieRepository.getMovieSearch(identifier, pageNumber))
            .thenReturn(Observable.error(Throwable(errorMessage)))

        val testObserver = searchMovieTask.buildUseCase(
            SearchMovieTask.Params(
                identifier,
                pageNumber
            )
        ).test()

        testObserver
            .assertSubscribed()
            .assertError { it.message?.equals(errorMessage, false) ?: false }
            .assertNotComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun test_searchMoveNoParams_error() {
        val testObserver = searchMovieTask.buildUseCase().test()
        testObserver.assertSubscribed()
    }
}