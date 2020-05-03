package com.niraj.contentsearch.di

import com.niraj.data.mapper.Mapper
import com.niraj.data.mapper.SearchDomainDataMapper
import com.niraj.data.model.MovieData
import com.niraj.data.repository.SearchRepositoryImpl
import com.niraj.domain.entities.MovieEntity
import com.niraj.domain.repository.SearchMovieRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {
    @Binds
    abstract fun bindRepository(repositoryImpl: SearchRepositoryImpl): SearchMovieRepository

    @Binds
    abstract fun bindMovieMapper(mapper: SearchDomainDataMapper): Mapper<MovieEntity, MovieData>
}