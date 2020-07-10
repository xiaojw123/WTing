package com.ml.wting.ui.home

import android.media.MediaPlayer
import android.text.TextUtils
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.ml.wting.R
import com.ml.wting.databinding.ActivityMvDetailBinding
import com.ml.wting.repository.viewmodel.MVDetailViewModel
import com.ml.wting.ui.base.BaseActivity
import com.ml.wting.util.Constant

class MVDetailActivity : BaseActivity<ActivityMvDetailBinding,MVDetailViewModel>(),
    MediaPlayer.OnPreparedListener {



    override fun initView() {

        val mvid=intent.getIntExtra(Constant.ID,Constant.INVALID_VALUE)
        if (mvid==Constant.INVALID_VALUE){
            return
        }
        mViewModel.getMVDetail(mvid).observe(this, Observer{
            mBindng.detail=it
            mBindng.mvDetailVideo.setVideoPath(it.brs.`480`)
            mBindng.mvDetailVideo.setOnPreparedListener(this)


        })
    }

    override fun getLayoutRes(): Int {

        return  R.layout.activity_mv_detail
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mBindng.mvDetailVideo.start()
    }

    override fun onPause() {
        super.onPause()
        mBindng.mvDetailVideo.stopPlayback()
    }

}