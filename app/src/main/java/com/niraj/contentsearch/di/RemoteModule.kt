package com.niraj.contentsearch.di

import com.niraj.contentsearch.BuildConfig
import com.niraj.data.model.MovieData
import com.niraj.data.repository.RemoteDataSource
import com.niraj.remote.api.SearchService
import com.niraj.remote.mapper.Mapper
import com.niraj.remote.mapper.MovieDataNetworkMapper
import com.niraj.remote.model.MoviesNetwork
import com.niraj.remote.source.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [RemoteModule.Binders::class])
class RemoteModule {

    @Module
    interface Binders {

        @Binds
        fun bindsRemoteSource(
            remoteDataSourceImpl: RemoteDataSourceImpl
        ): RemoteDataSource

        @Binds
        fun bindMoviesMapper(
            movieDataNetworkMapper: MovieDataNetworkMapper
        ): Mapper<MovieData, MoviesNetwork>
    }

    @Provides
    fun providesSearchService(retrofit: Retrofit): SearchService =
        retrofit.create(SearchService::class.java)


    @Provides
    fun providesRetrofit(): Retrofit =
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()

}