package com.inyomanw.mymovies.network

import com.inyomanw.mymovies.BuildConfig
import com.inyomanw.mymovies.data.model.PopularMovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("popular")
    fun getPopularMovies(@Query("api_key") apiKey: String = BuildConfig.BASE_API_KEY)
            : Single<PopularMovieResponse>
}