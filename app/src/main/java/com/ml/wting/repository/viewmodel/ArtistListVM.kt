package com.ml.wting.repository.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import com.ml.lib_base.rx.RxCallBack
import com.ml.wting.repository.model.SongItem
import com.ml.wting.ui.base.BaseViewModel

class ArtistListVM : BaseViewModel() {


    private val mArtistsLiveData by lazy {

        MutableLiveData<List<SongItem>>()
    }


    fun getSongList(id: Int): LiveData<List<SongItem>> {

        request(apiService.getPlayList(id), object : RxCallBack<JsonObject> {


            override fun onResut(result: JsonObject) {

                val tracksJS = result["playlist"].asJsonObject["tracks"].asJsonArray
                val songList = arrayListOf<SongItem>()
                tracksJS.forEach {

                    val songJS = it.asJsonObject
                    val albumJS = songJS["al"].asJsonObject
                    val songer = songJS["ar"].asJsonArray[0].asJsonObject["name"]


                    val songItem = SongItem(
                        songJS["id"].asInt,
                        songJS["name"].asString,
                        songer.asString,
                        albumJS["name"].asString,
                        albumJS["picUrl"].asString
                    )

                    songList.add(songItem)


                }
                mArtistsLiveData.value = songList


            }
        })



        return mArtistsLiveData

    }


    fun getArtists(id: Int): LiveData<List<SongItem>> {

        request(apiService.getSongerArtists(id), object : RxCallBack<JsonObject> {


            override fun onResut(result: JsonObject) {
                val hotSongs = result.getAsJsonArray("hotSongs")
                val artist = result.getAsJsonObject("artist")
                val songer = artist.get("name").asString

                val songList = arrayListOf<SongItem>()
                for (song in hotSongs) {
                    val songJS = song.asJsonObject
                    val alumJS = songJS.get("al").asJsonObject
                    val album = alumJS.get("name").asString
                    val albumPicUrl = alumJS.get("picUrl").asString

                    val songItem = SongItem(
                        songJS.get("id").asInt,
                        songJS.get("name").asString,
                        songer,
                        album,
                        albumPicUrl
                    )
                    songList.add(songItem)
                }
                mArtistsLiveData.value = songList
            }

        })

        return mArtistsLiveData


    }

}