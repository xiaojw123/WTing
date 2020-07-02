package com.ml.lib_base.net

import com.google.gson.JsonElement
import com.google.gson.JsonObject
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


















}