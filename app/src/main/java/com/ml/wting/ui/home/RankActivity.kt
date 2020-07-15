package com.ml.wting.ui.home

import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ml.lib_base.util.CommonUtil
import com.ml.lib_base.view.decoration.SpaceDecoration
import com.ml.wting.R
import com.ml.wting.repository.model.ArtistItem
import com.ml.wting.repository.model.MVEntity
import com.ml.wting.repository.model.RankEntity
import com.ml.wting.repository.model.SongListItem
import com.ml.wting.repository.viewmodel.RankViewModel
import com.ml.wting.ui.base.BaseActivity
import com.ml.wting.util.Constant
import com.ml.wting.util.Constant.PAGE_TYPE
import com.ml.wting.util.Constant.TYPE_DEFAULT
import com.ml.wting.util.Constant.TYPE_MV
import com.ml.wting.util.Constant.TYPE_RANKLIST
import com.ml.wting.util.Constant.TYPE_SONGER
import com.ml.wting.util.Constant.TYPE_SONGLIST
import com.ml.wting.view.adapter.RankAdapter

import kotlinx.android.synthetic.main.activity_rank.*
import java.util.*

class RankActivity : BaseActivity<ViewDataBinding, RankViewModel>() {


    override fun initView() {



        val page_from=intent.getStringExtra(Constant.PAGE_FROM)

        if (TextUtils.equals(page_from,Constant.HOME_PAGE_MORE)){


            initMore()
            return

        }


        val type = intent.getIntExtra(PAGE_TYPE, TYPE_DEFAULT)
        if (type == TYPE_DEFAULT) {
            CommonUtil.toast(this, "未知页面类型")
            return
        }

        rank_rlv.layoutManager = LinearLayoutManager(this)


        if (type == TYPE_MV) {
            initMV()

        }
        if (type== TYPE_SONGER){
            initSonger()
        }
        if (type==TYPE_SONGLIST){
            initSongList()
        }
        if (type==TYPE_RANKLIST){

            initRankList()
        }




    }

    private fun initMore() {
        val page_type=  intent.getIntExtra(PAGE_TYPE, TYPE_DEFAULT)
        when(page_type){
            HomeFragment.TYPE_M_RECOMMEND->{


                mViewModel.getNewSong().observe(this, Observer {

                    rank_rlv.layoutManager=GridLayoutManager(this,2)
                    rank_rlv.addItemDecoration(SpaceDecoration(10,true,0,this))
                    rank_rlv.adapter=RankAdapter(page_type,it!!)
                })

            }
            HomeFragment.TYPE_S_NEW->{

            }

        }




    }

    private fun initRankList() {
        mViewModel.getRankList().observe(this, Observer {
            rank_rlv.layoutManager=GridLayoutManager(this,2)
            rank_rlv.addItemDecoration(SpaceDecoration(10,true,0,this))
            rank_rlv.adapter=RankAdapter(TYPE_RANKLIST,it)
        })

    }

    private fun initSonger() {
        mViewModel.getSongerList().observe(this, Observer {

            rank_rlv.layoutManager=LinearLayoutManager(this)
            rank_rlv.adapter=RankAdapter<ArtistItem>(TYPE_SONGER,it)
        })
    }

    private fun initSongList() {

        mViewModel.getSongList().observe(this, Observer {


            rank_rlv.layoutManager=GridLayoutManager(this,2)
            rank_rlv.addItemDecoration(SpaceDecoration(10,true,0,this))
            rank_rlv.adapter=RankAdapter<SongListItem>(TYPE_SONGLIST,it)


        })

    }

    private fun initMV() {
        mViewModel.getRanMV().observe(this, Observer {


            val gm = GridLayoutManager(this, 2)
            gm.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    if (position == 0) {
                        return 2
                    }
                    return 1
                }

            }
            rank_rlv.layoutManager = gm
            rank_rlv.addItemDecoration(SpaceDecoration(5, true, 1, this))

            rank_rlv.adapter = RankAdapter(TYPE_MV, it)


        })
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_rank
    }


}
