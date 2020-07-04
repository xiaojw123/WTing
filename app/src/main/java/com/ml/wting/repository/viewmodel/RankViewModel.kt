package com.ml.wting.repository.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import com.ml.lib_base.rx.RxCallBack
import com.ml.lib_base.util.JsonUtil
import com.ml.wting.repository.model.ArtistItem
import com.ml.wting.repository.model.BaseResult
import com.ml.wting.repository.model.MVEntity
import com.ml.wting.repository.model.SongListItem
import com.ml.wting.ui.base.BaseViewModel

class RankViewModel : BaseViewModel() {


    private var mMVLiveData: MutableLiveData<List<MVEntity>>

    private var mSonngerLiveData: MutableLiveData<List<ArtistItem>>

    private var mSongListLiveData: MutableLiveData<List<SongListItem>>


//    ObservableField


    init {

        mMVLiveData = MutableLiveData()
        mSonngerLiveData = MutableLiveData()
        mSongListLiveData = MutableLiveData()

    }


    fun getRanMV(): LiveData<List<MVEntity>> {
        requestList(apiService!!.getRankNewMV(), object : RxCallBack<MVEntity> {
            override fun onResut(result: List<MVEntity>) {
                mMVLiveData.value = result
            }


        })














        return mMVLiveData


    }

    fun getSongerList(): LiveData<List<ArtistItem>> {

        request(apiService.getArtistList(), object : RxCallBack<JsonObject> {

            override fun onResut(result: JsonObject) {
                val listJS = result.get("list") as JsonObject
                val itemsJS = listJS.get("artists")

                val itemList = JsonUtil.toListBean(itemsJS, ArtistItem::class.java)

                mSonngerLiveData.value = itemList


            }

        })


        return mSonngerLiveData


    }


    fun getSongList(): LiveData<List<SongListItem>> {


        request(apiService.getHotSongList("hot"), object : RxCallBack<JsonObject> {


            override fun onResut(result: JsonObject) {

                val songList =
                    JsonUtil.toListBean(result.get("playlists"), SongListItem::class.java)

                mSongListLiveData.value = songList

            }
        })


        return mSongListLiveData


    }


}