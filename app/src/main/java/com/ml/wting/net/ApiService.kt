package com.ml.wting.net

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.ml.wting.repository.model.BaseResult
import com.ml.wting.repository.model.MVEntity
import com.ml.wting.repository.model.RankEntity
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {





    @GET("banner")
    fun getBanner(): Observable<JsonObject>


    @GET("personalized/newsong")
    fun getNewSong():Observable<JsonObject>


    @GET("personalized/mv")
    fun  getRecommendMV():Observable<JsonObject>

    @GET("top/playlist")
    fun  getHotSongList(@Query("order") order:String):Observable<JsonObject>





    @GET("mv/first")
    fun  getRankNewMV():Observable<BaseResult<MVEntity>>

    @GET("toplist/artist")
    fun  getArtistList():Observable<JsonObject>

    @GET("top/list")
    fun  getRankList(@Query("idx") id:Int):Flowable<JsonObject>


    @GET("mv/detail")
    fun getMVDetail(@Query("mvid") mvid:Int):Observable<JsonObject>

    @GET("artists")
    fun getSongerArtists(@Query("id") id:Int):Observable<JsonObject>

    @GET("song/detail")
    fun  getSongDetail(@Query("ids") ids:Int):Observable<JsonObject>

    @GET("song/url")
    fun  getSongUrl(@Query("id") id:Int):Observable<JsonObject>


    //歌单->歌单列表
    @GET("playlist/detail")
    fun getPlayList(@Query("id") id:Int):Observable<JsonObject>

























}