package com.niraj.contentsearch.di

import android.app.Application
import com.niraj.contentsearch.SearchContentApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        DataModule::class,
        DomainModule::class,
        LocalPersistenceModule::class,
        RemoteModule::class,
        PresentationModule::class
    ]
)
interface SearchContentAppComponent : AndroidInjector<SearchContentApp> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): SearchContentAppComponent
    }

    override fun inject(app: SearchContentApp)
}