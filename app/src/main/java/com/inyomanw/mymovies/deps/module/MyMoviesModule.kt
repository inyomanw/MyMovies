package com.inyomanw.mymovies.deps.module

import android.app.Application
import android.content.Context
import com.inyomanw.corelibrary.base.DiffCallback
import com.inyomanw.mymovies.data.model.ConnectionLiveData
import com.inyomanw.mymovies.data.repository.MyMoviesRepository
import com.inyomanw.mymovies.network.ApiInterface
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class MyMoviesModule {

    @Provides
    @Singleton
    fun providesContext(application: Application): Context {
        return application
    }


    @Provides
    @Singleton
    fun providesRepository(apiInterface: ApiInterface): MyMoviesRepository {
        return MyMoviesRepository(apiInterface)
    }


    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun providesDiffCallback() = DiffCallback()

    @Provides
    @Singleton
    fun providesConnectionLiveData(context: Context) = ConnectionLiveData(context)
}