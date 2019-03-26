package com.inyomanw.mymovies.deps.component

import android.app.Application
import com.inyomanw.mymovies.MyMovieApp
import com.inyomanw.mymovies.deps.module.ActivityBuilder
import com.inyomanw.mymovies.deps.module.FragmentBuilder
import com.inyomanw.mymovies.deps.module.MyMoviesModule
import com.inyomanw.mymovies.deps.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        AndroidSupportInjectionModule::class,
        ActivityBuilder::class,
        FragmentBuilder::class,
        NetworkModule::class,
        MyMoviesModule::class
    )
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(myMovieApp: MyMovieApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun network(networkModule: NetworkModule): Builder

        fun myMovies(myMoviesModule: MyMoviesModule): Builder

        fun build(): AppComponent
    }

}