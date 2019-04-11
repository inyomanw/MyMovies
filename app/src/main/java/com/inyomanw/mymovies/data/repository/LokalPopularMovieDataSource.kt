package com.inyomanw.mymovies.data.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import com.inyomanw.mymovies.data.local.AppDatabase
import com.inyomanw.mymovies.data.local.PopularMovie
import com.inyomanw.mymovies.data.local.dao.PopularMovieDao
import com.inyomanw.mymovies.data.local.entity.PopularMovieEntity
import io.reactivex.Single

class LokalPopularMovieDataSource(context: Context) {

    private lateinit var popularMovieDao: PopularMovieDao
    private lateinit var allMovies : LiveData<List<PopularMovieEntity>>

    init {
        AppDatabase.getInstance(context)?.let {
            popularMovieDao = it.popularMovieDao()
        }
    }


    fun getPopularMovies(): Single<List<PopularMovie>> {
        return popularMovieDao.findAllPopularMovie().map { list ->
            list.map { PopularMovie.from(it) }
        }
    }

    fun addPopularMovies(movies: List<PopularMovieEntity>?) {
        popularMovieDao.saveAll(movies)
    }
}