package com.inyomanw.mymovies.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.inyomanw.mymovies.data.local.entity.PopularMovieEntity
import io.reactivex.Single

@Dao
interface PopularMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(movies: List<PopularMovieEntity>?)

    @Query("SELECT * FROM popularmovie WHERE id = :id")
    fun findMovieById(id: Int): Single<PopularMovieEntity>

    @Query("SELECT * FROM popularmovie")
    fun findAllPopularMovie(): Single<List<PopularMovieEntity>>

    @Query("DELETE FROM popularmovie")
    fun deleteAllPopularMovie()
}