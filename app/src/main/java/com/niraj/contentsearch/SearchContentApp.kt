package com.niraj.contentsearch

import com.niraj.contentsearch.di.DaggerSearchContentAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class SearchContentApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerSearchContentAppComponent.builder().application(this).build()
    }

}