package com.ml.wting.ui.home

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.media.MediaPlayer
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.ml.wting.R
import com.ml.wting.databinding.ActivityMvBinding
import com.ml.wting.repository.viewmodel.MVDetailViewModel
import com.ml.wting.ui.base.BaseActivity
import com.ml.wting.util.Constant
import com.ml.wting.view.VideoCtrl

class MVDetailActivity : BaseActivity<ActivityMvBinding, MVDetailViewModel>(),
    MediaPlayer.OnPreparedListener {

    var mVCtrl: VideoCtrl? = null;
    var mPorvitHeight = 0;
    override fun initView() {

        val mvid = getId()
        if (mvid == Constant.INVALID_ID) {
            return
        }
        mViewModel.getMVDetail(mvid).observe(this, Observer {
            mBindng.detail = it
            mBindng.mvDetailVideo.setVideoPath(it.brs.`480`)
            mBindng.mvDetailVideo.setOnPreparedListener(this)
            mPorvitHeight = mBindng.mvDetailVideo.measuredHeight


        })
    }

    override fun getLayoutRes(): Int {

        return R.layout.activity_mv
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mBindng.mvDetailVideo.start()
        mVCtrl = VideoCtrl(this).apply {
            init(mBindng.mvDetailVideo)
        }

    }

    override fun onPause() {
        super.onPause()
        mVCtrl?.cancelProgressTimer()
        mBindng.mvDetailVideo.stopPlayback()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        when (newConfig.orientation) {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT -> {


                mBindng.mvDetailVideo.layoutParams =
                    ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        mPorvitHeight
                    )
                mBindng.mvDetailVideo.requestLayout()


            }
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE -> {
                mBindng.mvDetailVideo.layoutParams =
                    ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )

                mBindng.mvDetailVideo.requestLayout()


            }
        }

    }
}
