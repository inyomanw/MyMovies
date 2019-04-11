package com.inyomanw.mymovies.base

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.os.Build
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.WindowManager
import android.widget.TextView
import com.inyomanw.mymovies.R
import com.inyomanw.mymovies.data.remote.ConnectionLiveData
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var connectionLiveData: ConnectionLiveData

    private val disposable = CompositeDisposable()

    protected fun <T> LiveData<T>.onResult(action: (T) -> Unit) {
        observe(this@BaseActivity, Observer { data -> data?.let(action) })
    }

    protected fun boundNetwork(action: (Boolean) -> Unit = {}) {
        connectionLiveData.onResult {
            action.invoke(it)
        }
    }

    protected fun Disposable.track() {
        disposable.add(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        onInterceptCreate()
        super.onCreate(savedInstanceState)
        onSetupLayout(savedInstanceState)
        onViewReady(savedInstanceState)
    }

    fun setupToolbarProperties(
        toolbarId: Toolbar,
        tvTitle: TextView? = null,
        @StringRes title: Int = R.string.empty_string,
        @DrawableRes drawable: Int? = R.drawable.ch_ic_arrow_left
    ) {
        setSupportActionBar(toolbarId)
        supportActionBar?.let {
            it.setDisplayShowTitleEnabled(false)
            it.setDisplayHomeAsUpEnabled(
                null != drawable
            )
            it.setDisplayShowHomeEnabled(null != drawable)
            drawable?.let { iconUp ->
                it.setHomeAsUpIndicator(iconUp)
            }
        }
        tvTitle?.setText(title)
    }

    fun changeStatusBarColor(color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = color
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    protected open fun onInterceptCreate() {}
    protected abstract fun onSetupLayout(savedInstanceState: Bundle?)
    protected abstract fun onViewReady(savedInstanceState: Bundle?)

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }

}