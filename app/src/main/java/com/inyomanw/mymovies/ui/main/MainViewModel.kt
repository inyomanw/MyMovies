package com.inyomanw.mymovies.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.inyomanw.mymovies.base.BaseViewModel
import com.inyomanw.mymovies.data.model.PopularMovieModel
import com.inyomanw.mymovies.data.repository.MyMoviesRepository
import com.inyomanw.mymovies.deps.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class MainViewModel @Inject constructor(private val repo: MyMoviesRepository) : BaseViewModel() {

    private val moviesLiveData = MutableLiveData<List<PopularMovieModel>>()

    init {
        moviesLiveData.value = listOf()
    }

    fun observeMovies() : LiveData<List<PopularMovieModel>> = moviesLiveData

    fun getPopularMovies(){
        repo.getPopularMoview().onResult(
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