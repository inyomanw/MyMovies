package com.inyomanw.mymovies.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.inyomanw.mymovies.R

fun ImageView.onLoad(context: Context,url :String){
    Glide.with(context)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.ch_ic_arrow_left)
        .into(this)
}
