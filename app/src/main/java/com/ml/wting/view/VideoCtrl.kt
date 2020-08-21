package com.ml.wting.view

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AppComponentFactory
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.ml.lib_base.rx.RxHelper
import com.ml.lib_base.util.APPLOG
import com.ml.lib_base.util.CommonUtil
import com.ml.wting.R
import com.ml.wting.ui.base.BaseActivity
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

import java.util.concurrent.TimeUnit
import kotlin.concurrent.fixedRateTimer

class VideoCtrl(context: Context) : View.OnClickListener, SeekBar.OnSeekBarChangeListener {


    lateinit var mPlay: ImageView
    lateinit var mFullScreen: ImageView
    lateinit var mProgressBar: SeekBar
    lateinit var mLen: TextView
    lateinit var mCurrent: TextView
    lateinit var mVideoView: VideoView
    var mTimerFlowable: Disposable? = null
    val mContext: Context

    init {
        mContext = context

    }


    fun init(videoView: VideoView) {
        mVideoView = videoView

        APPLOG.printDebug("parent INFO__" + videoView.parent)
        val rootContainer = videoView.parent as ViewGroup
        val ctrl_view = CommonUtil.inflater(
            mContext,
            R.layout.video_ctrl_layout,
            rootContainer
        )
        val params = ViewGroup.LayoutParams(videoView.measuredWidth, videoView.measuredHeight)
        rootContainer.addView(ctrl_view, params)
        mPlay = ctrl_view.findViewById(R.id.ctrl_play_img)
        mFullScreen = ctrl_view.findViewById(R.id.ctrl_fullscreen_img)
        mProgressBar = ctrl_view.findViewById(R.id.ctrl_skb)
        mLen = ctrl_view.findViewById(R.id.ctrl_tolatime_tv)
        mCurrent = ctrl_view.findViewById(R.id.ctrl_curtime_tv)
        CommonUtil.setOnClicks(
            this,
            mPlay,
            mFullScreen
        )
        mLen.text = formatTimeLen(mVideoView.duration)
        mProgressBar.setOnSeekBarChangeListener(this)
        mProgressBar.max = mVideoView.duration
        startProgressTimer()


    }

    private fun startProgressTimer() {

        mTimerFlowable = Flowable.interval(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {


                val curPos = mVideoView.currentPosition
                if (curPos > mProgressBar.max) {
                    mTimerFlowable?.dispose()
                } else {
                    mProgressBar.setProgress(curPos)
                    mCurrent.setText(formatTimeLen(curPos))
                }

            }


    }

    fun cancelProgressTimer() {

        mTimerFlowable?.apply {

            if (!isDisposed) {

                dispose()
            }

        }

    }


    fun formatTimeLen(mss: Int): String {
        val ss = mss / 1000
        val m = ss / 60
        val s = ss % 60


        val mstr = if (m < 10) "0$m" else m

        val sstr = if (s < 10) "0$s" else s

        return "$mstr:$sstr"

    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onClick(v: View?) {
        when (v) {
            mPlay -> {

                if (mVideoView.isPlaying) {
                    mVideoView.pause()
                    mPlay.setImageResource(R.mipmap.ic_action_play)
                } else {
                    mVideoView.start()
                    mPlay.setImageResource(R.mipmap.ic_action_pause)
                }


            }
            mFullScreen -> {

                val or = mContext.resources.configuration.orientation

                if (or == Configuration.ORIENTATION_PORTRAIT) {
                    mFullScreen.setImageResource(R.mipmap.ic_action_fullscreen_exit)
                    (mContext as AppCompatActivity).requestedOrientation =
                        ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                } else {
                    mFullScreen.setImageResource(R.mipmap.ic_action_fullscreen)
                    (mContext as AppCompatActivity).requestedOrientation =
                        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                }


            }
        }
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
    }

}