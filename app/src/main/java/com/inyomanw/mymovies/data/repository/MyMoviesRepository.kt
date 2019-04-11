package com.inyomanw.mymovies.data.repository

import android.util.Log
import com.inyomanw.mymovies.data.local.PopularMovie
import com.inyomanw.mymovies.data.local.entity.PopularMovieEntity
import com.inyomanw.mymovies.data.remote.DetailMovieResponse
import com.inyomanw.mymovies.data.remote.NowPlayingResult
import com.inyomanw.mymovies.data.remote.PopularMovieModel
import com.inyomanw.mymovies.network.ApiInterface
import io.reactivex.Single

class MyMoviesRepository(
    private var apiInterface: ApiInterface,
    private var lokalPopularMovieDataSource: LokalPopularMovieDataSource
) {
    fun getPopularMovieFromRemote(): Single<List<PopularMovie>> {
        return apiInterface.getPopularMovies()
            .doOnSuccess {
                lokalPopularMovieDataSource.addPopularMovies(
                    it.results?.map { data -> PopularMovieEntity.from(data) })
            }
            .map {
                it.results?.map { data -> PopularMovie.from(data) }
            }

    }

    fun getDetailMovie(id: Int): Single<DetailMovieResponse> {
        return apiInterface.getDetailMovie(id)
            .map { it }
    }

    fun getNowPlayingMovie(): Single<List<NowPlayingResult>> {
        return apiInterface.getNowPlayingMovies()
            .map {
                it.getNowPlayingResult()
            }
    }

    fun getPopularMovies(): Single<List<PopularMovie>> {
        return lokalPopularMovieDataSource.getPopularMovies()
            .flatMap { list ->
                list?.let { Single.just(it) } ?: kotlin.run { getPopularMovieFromRemote() }
            }
            .doAfterSuccess { getPopularMovieFromRemote() }

    }

}