package com.inyomanw.mymovies.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.inyomanw.corelibrary.base.DiffCallback
import com.inyomanw.corelibrary.base.GeneralRecyclerViewAdapter
import com.inyomanw.mymovies.BuildConfig
import com.inyomanw.mymovies.R
import com.inyomanw.mymovies.base.BaseActivity
import com.inyomanw.mymovies.data.model.PopularMovieModel
import com.inyomanw.mymovies.ui.detail.DetailActivity
import com.inyomanw.mymovies.utils.onLoad
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.viewholder_movie.view.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var diffCallback: DiffCallback

    @Inject
    lateinit var viewModel: MainViewModel

    private var isConnected = true

    private val moviesAdapter by lazy {
        GeneralRecyclerViewAdapter<PopularMovieModel>(
            diffCallback = diffCallback,
            holderResId = R.layout.viewholder_movie,
            onBind = { model, view ->
                populateDisplayMovies(model, view)
            },
            itemListener = { model, _, _ ->
                Toast.makeText(this@MainActivity, "${model.id}", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, DetailActivity::class.java).apply {
                    putExtra("ID", model.id)
                })
            }
        )
    }

    override fun onSetupLayout(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        val snapHelper = LinearSnapHelper()
        with(rv_movies) {
            layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
            adapter = moviesAdapter
            snapHelper.attachToRecyclerView(this)
        }
        tv_see_more.setOnClickListener {
            startActivity(Intent(this@MainActivity, SeeMoreActivity::class.java))
        }
        observingData()
    }

    private fun observingData() {
        viewModel.observeMovies().onResult { action ->
            action?.let {
                moviesAdapter.setData(it.take(8))
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

    private fun populateDisplayMovies(model: PopularMovieModel, itemView: View) {
        with(itemView) {
            imv_image.onLoad(this@MainActivity, "${BuildConfig.BASE_IMAGE_URL}${model.posterPath}")
            tv_title.text = model.title
            tv_rating.text = model.voteAverage.toString()
        }
    }


}
