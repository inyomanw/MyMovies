package com.inyomanw.mymovies.ui.detail

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.view.ViewCompat
import com.inyomanw.corelibrary.utils.calculateTwoDates
import com.inyomanw.mymovies.BuildConfig
import com.inyomanw.mymovies.R
import com.inyomanw.mymovies.base.BaseActivity
import com.inyomanw.mymovies.data.model.DetailMovieResponse
import com.inyomanw.mymovies.utils.onLoad
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : BaseActivity(), AppBarLayout.OnOffsetChangedListener {

    @Inject
    lateinit var viewModel: DetailViewModel

    private val PERCENTAGE_TO_SHOW_IMAGE = 20
    private var mMaxScrollSize: Int = 0
    private var mIsImageHidden: Boolean = false
    var getId = 0


    override fun onSetupLayout(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_detail)
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        appbar.addOnOffsetChangedListener(this)
        getId = intent.getIntExtra("ID", 0)
        observeData()
    }

    private fun observeData() {
        viewModel.observeDetailResponse().onResult { data ->
            data?.let {
                populateDisplay(it)
            }
        }
        fetchDetail()
    }

    private fun fetchDetail() {
        boundNetwork {
            if (it) viewModel.getDetailResponse(getId)
        }
    }

    private fun populateDisplay(data: DetailMovieResponse) {
        imv_background.onLoad(this@DetailActivity, "${BuildConfig.BASE_IMAGE_URL}${data.backdropPath}")
        imv_detail.onLoad(this@DetailActivity, "${BuildConfig.BASE_IMAGE_URL}${data.posterPath}")
        tv_title.text = data.title
        tv_overview.text = data.overview
        data.releaseDate?.let { tv_date.text = this.calculateTwoDates(it) }
    }


    override fun onOffsetChanged(appBarLayout: AppBarLayout?, i: Int) {
        if (mMaxScrollSize == 0)
            appBarLayout?.let {
                mMaxScrollSize = it.totalScrollRange
            }

        val currentScrollPercentage = Math.abs(i) * 100 / mMaxScrollSize

        when {
            currentScrollPercentage >= PERCENTAGE_TO_SHOW_IMAGE -> {
                if (!mIsImageHidden) {
                    mIsImageHidden = true
                    ViewCompat.animate(imv_detail).scaleY(0f).scaleX(0f).start()
                }
            }
            currentScrollPercentage < PERCENTAGE_TO_SHOW_IMAGE -> {
                if (mIsImageHidden) {
                    mIsImageHidden = false
                    ViewCompat.animate(imv_detail).scaleY(1f).scaleX(1f).start()
                }
            }
        }
    }
}
