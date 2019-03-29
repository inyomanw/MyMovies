package com.inyomanw.mymovies.ui.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.inyomanw.corelibrary.base.DiffCallback
import com.inyomanw.corelibrary.base.GeneralRecyclerViewAdapter
import com.inyomanw.mymovies.BuildConfig
import com.inyomanw.mymovies.R
import com.inyomanw.mymovies.base.BaseActivity
import com.inyomanw.mymovies.data.model.PopularMovieModel
import com.inyomanw.mymovies.utils.onLoad
import kotlinx.android.synthetic.main.activity_see_more.*
import kotlinx.android.synthetic.main.viewholder_more_movie.view.*
import javax.inject.Inject

class SeeMoreActivity : BaseActivity() {
    @Inject
    lateinit var diffCallback: DiffCallback

    @Inject
    lateinit var viewModel: MainViewModel

    private var isConnected = true

    private val movieAdapter by lazy {
        GeneralRecyclerViewAdapter<PopularMovieModel>(
            diffCallback = diffCallback,
            holderResId = R.layout.viewholder_more_movie,
            onBind = {model, view ->
                populateDisplay(model,view)
            }
        )
    }

    override fun onSetupLayout(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_see_more)

    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        with(rv_movie_more){
            layoutManager = LinearLayoutManager(context)
            adapter=movieAdapter
        }
        observeData()
    }

    private fun observeData(){
        viewModel.observeMovies().onResult { action ->
            action?.let {
                movieAdapter.setData(it)
            }
        }
        fetchMovies()
    }

    private fun fetchMovies() {
        boundNetwork {
            if (it) viewModel.getPopularMovies()
            else isConnected = false
        }
    }

    private fun populateDisplay(model : PopularMovieModel, itemView : View){
        with(itemView){
            imv_poster.onLoad(this@SeeMoreActivity,"${BuildConfig.BASE_IMAGE_URL}${model.posterPath}")
            tv_rating.text=model.voteAverage.toString()
            tv_count_rate.text = model.voteCount.toString()
            tv_overview.text = model.overview
        }
    }

}
