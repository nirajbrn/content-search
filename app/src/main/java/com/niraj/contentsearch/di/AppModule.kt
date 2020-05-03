package com.niraj.contentsearch.di

import android.app.Application
import android.content.Context
import com.niraj.contentsearch.HomeActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {
    @Binds
    abstract fun bindContext(application: Application): Context

    @ContributesAndroidInjector
    internal abstract fun contributesMainActivity(): HomeActivity

}