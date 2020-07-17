package com.ml.wting.repository.model

data class RankEntity(

    val id:Long,

    val name:String,
    val coverImgUrl:String,

    val songList:List<SongItem>

)
