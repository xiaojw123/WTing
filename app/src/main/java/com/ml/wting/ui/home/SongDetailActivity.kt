package com.ml.wting.ui.home

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.ml.lib_base.util.DrawableUtil
import com.ml.wting.R
import com.ml.wting.databinding.ActivitySongDetailBinding
import com.ml.wting.repository.viewmodel.SongDeailVM
import com.ml.wting.ui.base.BaseActivity
import com.ml.wting.util.Constant

class SongDetailActivity : BaseActivity<ActivitySongDetailBinding, SongDeailVM>(),
    MediaPlayer.OnPreparedListener {


    override fun initView() {
        val id = getId()
        mViewModel.getDetail(id).observe(this, Observer {

            mBindng.detail = it
            DrawableUtil.load(this, it.albumPicUrl, mBindng.songDetailImg)


        })
        mViewModel.getPlayUrl(id).observe(this, Observer {


            val player = MediaPlayer.create(this, Uri.parse(it))
            player.setOnPreparedListener(this)


        })

    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_song_detail
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mp?.start()
    }

}
