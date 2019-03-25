package com.inyomanw.mymovies

import com.inyomanw.mymovies.deps.component.AppComponent
import com.inyomanw.mymovies.deps.component.DaggerAppComponent
import com.inyomanw.mymovies.deps.module.MyMoviesModule
import com.inyomanw.mymovies.deps.module.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class MyMovieApp : DaggerApplication() {


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent : AppComponent by lazy {
            DaggerAppComponent.builder()
                .network(NetworkModule())
                .myMovies(MyMoviesModule())
                .build()
        }
        return  appComponent
    }

}