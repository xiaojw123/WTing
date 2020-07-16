package com.ml.wting.ui.home

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ml.wting.R
import com.ml.wting.repository.model.SongItem
import com.ml.wting.repository.viewmodel.ArtistListVM
import com.ml.wting.ui.base.BaseActivity
import com.ml.wting.util.Constant
import com.ml.wting.view.adapter.ArtistListAdapter
import kotlinx.android.synthetic.main.activity_songer_songlist.*

/**
 *
 * 排行->歌手->歌单列表
 * 歌单->歌单列表
 */

class SonglistActivity : BaseActivity<ViewDataBinding, ArtistListVM>() {


    override fun initView() {
        artist_rlv.layoutManager = LinearLayoutManager(this)
        val pageType = intent.getIntExtra(Constant.PAGE_TYPE, Constant.TYPE_DEFAULT)
        if (pageType == Constant.TYPE_RANKLIST) {
            val songList = intent.getParcelableArrayListExtra<SongItem>(Constant.SONG_LIST)
            artist_rlv.adapter =
                ArtistListAdapter(songList)

        } else {
            val id = intent.getIntExtra(Constant.ID, Constant.INVALID_VALUE)



            when (pageType) {
                Constant.TYPE_SONGER -> {
                    mViewModel.getArtists(id).observe(this, Observer {


                        artist_rlv.adapter = ArtistListAdapter(it)

                    })


                }
                Constant.TYPE_SONGLIST -> {

                    mViewModel.getSongList(id).observe(this, Observer {


                        artist_rlv.adapter = ArtistListAdapter(it)

                    })

                }

            }


        }


    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_songer_songlist
    }
}
