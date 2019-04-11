package com.inyomanw.mymovies.data.local.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.inyomanw.mymovies.data.remote.PopularMovieModel

@Entity(tableName = "popularmovie")
data class PopularMovieEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double?,
    val title: String?,
    @ColumnInfo(name = "poster_path")
    val posterPath: String?,
    val overview: String?,
    @ColumnInfo(name = "release_date")
    val releaseDate: String?,
    val popularity: Double?,
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String?,
    @ColumnInfo(name ="vote_count")
    val voteCount: Int?
) {
    companion object {
        fun from(data: PopularMovieModel) = PopularMovieEntity(
            data.id, data.voteAverage, data.title,
            data.posterPath, data.overview, data.releaseDate,
            data.popularity, data.backdropPath,data.voteCount
        )
    }
}