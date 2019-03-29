package com.inyomanw.mymovies.data.repository

import com.inyomanw.mymovies.data.model.DetailMovieResponse
import com.inyomanw.mymovies.data.model.NowPlayingResult
import com.inyomanw.mymovies.data.model.PopularMovieModel
import com.inyomanw.mymovies.network.ApiInterface
import io.reactivex.Single

class MyMoviesRepository(private var apiInterface: ApiInterface) {
    fun getPopularMoview(): Single<List<PopularMovieModel>> {
        return apiInterface.getPopularMovies()
            .map {
                it.getPopularMovie()
            }
    }

    fun getDetailMovie(id: Int): Single<DetailMovieResponse> {
        return apiInterface.getDetailMovie(id)
            .map { it }
    }

    fun getNowPlayingMovie() : Single<List<NowPlayingResult>> {
        return apiInterface.getNowPlayingMovies()
            .map {
                it.getNowPlayingResult()
            }
    }

}