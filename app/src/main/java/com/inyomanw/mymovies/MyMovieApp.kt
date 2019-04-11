package com.inyomanw.mymovies

import com.inyomanw.corelibrary.deps.NetworkModule
import com.inyomanw.corelibrary.deps.UtilModule
import com.inyomanw.mymovies.deps.component.AppComponent
import com.inyomanw.mymovies.deps.component.DaggerAppComponent
import com.inyomanw.mymovies.deps.module.MyMoviesModule
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import okhttp3.Cache
import java.io.IOException
import javax.inject.Inject

class MyMovieApp : DaggerApplication() {

    @Inject
    lateinit var httpCache : Cache


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent : AppComponent by lazy {
            DaggerAppComponent.builder()
                .application(this)
                .util(UtilModule(BuildConfig.BASE_SHARED_PREFERENCES,this))
                .network(NetworkModule(BuildConfig.BASE_URL_HARDCODE,this))
                .myMovies(MyMoviesModule())
                .build()
        }
        return  appComponent
    }

    private fun clearHttpCache() {
        try {
            httpCache.evictAll()
        } catch (e: IOException) {
            e.message
        }
    }


}