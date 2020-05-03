package com.niraj.contentsearch.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.niraj.domain.entities.MovieEntity
import com.niraj.presentation.factory.ViewModelFactory
import com.niraj.presentation.mapper.Mapper
import com.niraj.presentation.mapper.SearchEntityMapper
import com.niraj.presentation.model.Movie
import com.niraj.presentation.viewmodels.SearchMovieVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PresentationModule {

    @Binds
    abstract fun bindsViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SearchMovieVM::class)
    abstract fun bindsMoviesViewModel(searchMovieVM: SearchMovieVM): ViewModel

    @Binds
    abstract fun bindsMovieDataMapper(
        searchEntityMapper: SearchEntityMapper
    ): Mapper<MovieEntity, Movie>

}