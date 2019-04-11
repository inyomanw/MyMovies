package com.inyomanw.mymovies.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.inyomanw.corelibrary.deps.ActivityScoped
import com.inyomanw.mymovies.base.BaseViewModel
import com.inyomanw.mymovies.data.local.PopularMovie
import com.inyomanw.mymovies.data.remote.PopularMovieModel
import com.inyomanw.mymovies.data.repository.MyMoviesRepository
import javax.inject.Inject

@ActivityScoped
class MainViewModel @Inject constructor(private val repo: MyMoviesRepository) : BaseViewModel() {

    private val moviesLiveData = MutableLiveData<List<PopularMovie>>()

    init {
        moviesLiveData.value = listOf()
    }

    fun observeMovies() : LiveData<List<PopularMovie>> = moviesLiveData

    fun getPopularMovies(){
        repo.getPopularMovies().onResult(
            {
                isError.postValue(null)
                isEmtyData.postValue(it.isNullOrEmpty())
                moviesLiveData.postValue(it)
            },
            {
                isError.postValue(it)
            }
        )
    }
}