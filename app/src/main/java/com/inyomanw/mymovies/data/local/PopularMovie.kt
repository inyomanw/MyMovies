package com.inyomanw.mymovies.data.local

import com.inyomanw.mymovies.data.local.entity.PopularMovieEntity
import com.inyomanw.mymovies.data.remote.PopularMovieModel

data class PopularMovie(
    val id: Int?,
    val title: String?,
    val image: String?,
    val vote: Double?,
    val description: String?,
    val releaseDate: String?,
    val voteCount: Int?

) {
    companion object {
        fun from(data: PopularMovieEntity) = PopularMovie(
            data.id,
            data.title,
            data.posterPath,
            data.voteAverage,
            data.overview,
            data.releaseDate,
            data.voteCount
        )

        fun from(data: PopularMovieModel) = PopularMovie(
            data.id,
            data.title,
            data.posterPath,
            data.voteAverage,
            data.overview,
            data.releaseDate,
            data.voteCount
        )
    }
}