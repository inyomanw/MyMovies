package com.inyomanw.mymovies.network

import com.inyomanw.mymovies.BuildConfig
import com.inyomanw.mymovies.data.remote.DetailMovieResponse
import com.inyomanw.mymovies.data.remote.NowPlayingMovieResponse
import com.inyomanw.mymovies.data.remote.PopularMovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = BuildConfig.BASE_API_KEY,
        @Query("page") page: Int = 1
    ): Single<PopularMovieResponse>

    @GET("now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String = BuildConfig.BASE_API_KEY,
        @Query("page") page: Int = 1
    ): Single<NowPlayingMovieResponse>

    @GET("{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.BASE_API_KEY
    ): Single<DetailMovieResponse>
}