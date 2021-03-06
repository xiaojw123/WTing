package com.ml.wting.repository.viewmodel

import android.text.TextUtils
import android.util.SparseArray
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import com.ml.lib_base.rx.RxCallBack
import com.ml.lib_base.util.APPLOG
import com.ml.lib_base.util.JsonUtil
import com.ml.wting.repository.model.*
import com.ml.wting.ui.base.BaseViewModel
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.intellij.lang.annotations.Flow
import org.json.JSONObject
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.util.function.Consumer

class RankViewModel : BaseViewModel() {


    private val mMVLiveData by lazy {
        MutableLiveData<List<MVEntity>>()
    }

    private val mSonngerLiveData by lazy {
        MutableLiveData<List<ArtistItem>>()

    }

    private val mSongListLiveData by lazy {

        MutableLiveData<List<SongListItem>>()

    }
    private val mRanLiveData by lazy {

        MutableLiveData<List<RankEntity>>()
    }
    private val newSongData by lazy {


        MutableLiveData<List<CategoryItem>?>()
    }


    fun getNewSong(): LiveData<List<CategoryItem>?> {

        request(apiService?.getNewSong()!!, object : RxCallBack<JsonObject> {
            override fun onResut(result: JsonObject) {

                val songs = JsonUtil.toListBean(result.get("result"), CategoryItem::class.java)

                newSongData.value = songs
            }


        })

        return newSongData


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

    //榜单排行
    fun getRankList(): LiveData<List<RankEntity>> {

        val flowableArray = SparseArray<Flowable<JsonObject>>()
        for (i in 0..3) {
            flowableArray.put(i, apiService.getRankList(i))
        }


        val mergedFlowable = Flowable.merge(
            flowableArray[0]!!,
            flowableArray[1]!!,
            flowableArray[2]!!,
            flowableArray[3]!!
        )
        val rankEntitys = arrayListOf<RankEntity>()


        val f =
            mergedFlowable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    APPLOG.printDebug("resJS__" + it)
                    val playListJS = it["playlist"].asJsonObject

                    val tracksJArray = playListJS["tracks"].asJsonArray

                    val songList = arrayListOf<SongItem>()

                    tracksJArray.forEach {

                        val itemJS = it.asJsonObject
                        val songItem = SongItem(
                            itemJS["id"].asLong,
                            itemJS["name"].asString,
                            itemJS["ar"].asJsonArray[0].asJsonObject["name"].asString,
                            itemJS["al"].asJsonObject["name"].asString,
                            itemJS["al"].asJsonObject["picUrl"].asString
                        )

                        songList.add(songItem)

                    }
                    val entity = RankEntity(
                        playListJS["id"].asLong, playListJS["name"].asString,
                        playListJS["coverImgUrl"].asString,
                        songList
                    )
                    rankEntitys.add(entity)
                    if (rankEntitys.size == 4) {
                        APPLOG.printDebug("rankEnit Livedata +++")
                        mRanLiveData.value = rankEntitys
                    }


                })
        taskDisposables.add(f)

        return mRanLiveData

    }


}