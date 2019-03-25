package com.inyomanw.mymovies.deps.module

import android.annotation.SuppressLint
import com.inyomanw.mymovies.MainActivity
import com.inyomanw.mymovies.deps.ActivityScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Suppress("ReplaceArrayOfWithLiteral","unused")
@SuppressLint("UNUSED")
@Module
abstract class ActivityBuilder {
    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun bindMainActivity() : MainActivity
}