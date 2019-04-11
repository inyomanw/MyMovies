package com.inyomanw.mymovies.base

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import com.inyomanw.mymovies.data.remote.ConnectionLiveData
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment(){

    @Inject
    lateinit var connectionLiveData : ConnectionLiveData

    protected fun <T> LiveData<T>.onResult(action: (T) -> Unit) {
        observe(this@BaseFragment, Observer { data -> data?.let(action) })
    }

    protected fun boundNetwork(action: (Boolean) -> Unit = {}) {
        connectionLiveData.onResult {
            action.invoke(it)
        }
    }
}