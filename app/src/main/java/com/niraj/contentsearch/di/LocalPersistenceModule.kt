package com.niraj.contentsearch.di

import android.app.Application
import com.niraj.data.model.MovieData
import com.niraj.data.repository.LocalDataSource
import com.niraj.local.database.MovieDetailDB
import com.niraj.local.mapper.Mapper
import com.niraj.local.mapper.MovieDataLocalMapper
import com.niraj.local.model.MovieLocal
import com.niraj.local.source.LocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [LocalPersistenceModule.Binders::class])
class LocalPersistenceModule {

    @Module
    interface Binders {

        @Binds
        fun bindsLocalDataSource(
            localDataSourceImpl: LocalDataSourceImpl
        ): LocalDataSource

        @Binds
        fun bindMovieDataMapper(
            movieDataLocalMapper: MovieDataLocalMapper
        ): Mapper<MovieData, MovieLocal, String>
    }

    @Provides
    @Singleton
    fun providesDatabase(
        application: Application
    ) = MovieDetailDB.getInstance(application.applicationContext)

    @Provides
    @Singleton
    fun providesMovieDetailDAO(
        movieDetailDB: MovieDetailDB
    ) = movieDetailDB.getMovieDao()

}
