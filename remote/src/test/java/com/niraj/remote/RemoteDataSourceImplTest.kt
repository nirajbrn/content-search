package com.niraj.remote

import com.niraj.data.repository.RemoteDataSource
import com.niraj.remote.api.SearchService
import com.niraj.remote.mapper.MovieDataNetworkMapper
import com.niraj.remote.model.ResponseWrapper
import com.niraj.remote.source.RemoteDataSourceImpl
import com.niraj.remote.utils.TestDataGenerator
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class RemoteDataSourceImplTest {

    @Mock
    private lateinit var searchService: SearchService
    private val movieDataNetworkMapper = MovieDataNetworkMapper()

    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        remoteDataSource = RemoteDataSourceImpl(movieDataNetworkMapper, searchService)
    }

    @Test
    fun test_getMovieList_success() {
        val movieResponseData = TestDataGenerator.generateSearchResult()
        val movieName = "Jungle"
        val apiKey = "c4c6ccd7"

        val mockResponse = ResponseWrapper(movieResponseData)
        Mockito.`when`(searchService.getSearchMovieData(movieName, apiKey))
            .thenReturn(Observable.just(mockResponse))

        remoteDataSource.getSearchData(movieName, 1)
            .test()
            .assertSubscribed()
            .assertValue { movieList ->
                movieList.containsAll(movieResponseData.map {
                    movieDataNetworkMapper.from(
                        it
                    )
                })
            }
    }

}