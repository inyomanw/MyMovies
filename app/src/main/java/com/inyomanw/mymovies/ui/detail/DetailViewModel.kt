package com.inyomanw.mymovies.ui.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.inyomanw.mymovies.base.BaseViewModel
import com.inyomanw.mymovies.data.remote.DetailMovieResponse
import com.inyomanw.mymovies.data.repository.MyMoviesRepository
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val repository: MyMoviesRepository) : BaseViewModel(){

    private val detailResponse = MutableLiveData<DetailMovieResponse>()

    fun observeDetailResponse() : LiveData<DetailMovieResponse> = detailResponse

    fun getDetailResponse(id : Int){
        repository.getDetailMovie(id).onResult({
            isError.postValue(null)
            detailResponse.postValue(it)
        },{
            isError.postValue(it)
        })
    }

}