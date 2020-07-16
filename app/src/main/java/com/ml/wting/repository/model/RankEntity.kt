package com.ml.wting.repository.model

import com.google.gson.JsonObject

data class RankEntity(

    val id:Int,

    val name:String,
    val coverImgUrl:String,

    val songList:List<SongItem>

)
