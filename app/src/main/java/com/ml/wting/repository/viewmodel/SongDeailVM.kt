package com.ml.wting.repository.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import com.ml.lib_base.rx.RxCallBack
import com.ml.wting.repository.model.SongItem
import com.ml.wting.ui.base.BaseViewModel

class SongDeailVM : BaseViewModel() {


    private val mDetailLD by lazy {
        MutableLiveData<SongItem>()
    }
    private val mUrlLD by lazy {

        MutableLiveData<String>()

    }


    fun getDetail(ids: Long): LiveData<SongItem> {
        request(apiService.getSongDetail(ids),

            object : RxCallBack<JsonObject> {


                override fun onResut(result: JsonObject) {
                    if (result.has("songs")) {


                        val songsJArray = result["songs"].asJsonArray
                        if (songsJArray.size() > 0) {


                            val songJS = songsJArray[0].asJsonObject
                            val songer = songJS["ar"].asJsonArray[0].asJsonObject["name"]
                            val albumJS = songJS["al"].asJsonObject
                            val item = SongItem(
                                songJS["id"].asLong,
                                songJS["name"].asString,
                                songer.asString,
                                albumJS["name"].asString,
                                albumJS["picUrl"].asString
                            )
                            mDetailLD.value = item
                        }
                    }
                }


            }
        )
        return mDetailLD
    }

    fun getPlayUrl(id: Long): LiveData<String> {

        request(apiService.getSongUrl(id),
            object : RxCallBack<JsonObject> {

                override fun onResut(result: JsonObject) {
                    val urlS = result["data"].asJsonArray[0].asJsonObject
                    mUrlLD.value = urlS["url"].asString

                }
            })

        return mUrlLD


    }

}