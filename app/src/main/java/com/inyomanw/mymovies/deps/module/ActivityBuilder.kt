package com.inyomanw.mymovies.deps.module

import android.annotation.SuppressLint
import com.inyomanw.corelibrary.deps.ActivityScoped
import com.inyomanw.mymovies.ui.main.MainActivity
import com.inyomanw.mymovies.ui.detail.DetailActivity
import com.inyomanw.mymovies.ui.main.SeeMoreActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Suppress("ReplaceArrayOfWithLiteral","unused")
@SuppressLint("UNUSED")
@Module
abstract class ActivityBuilder {
    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun bindMainActivity() : MainActivity

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun bindDetailActivity() : DetailActivity

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun bindSeeMoreActivity() : SeeMoreActivity
}