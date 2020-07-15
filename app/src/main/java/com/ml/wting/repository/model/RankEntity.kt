package com.ml.wting.repository.model

import com.google.gson.JsonObject

data class RankEntity(
    val code: Int,
    val playlist: Playlist,
    val privileges: List<Privilege>,
    val relatedVideos: Any,
    val urls: Any


) {
    lateinit var songList: List<SongItem>
}

data class Playlist(
    val ToplistType: String,
    val adType: Int,
    val backgroundCoverId: Int,
    val backgroundCoverUrl: Any,
    val cloudTrackCount: Int,
    val commentCount: Int,
    val commentThreadId: String,
    val coverImgId: Long,
    val coverImgId_str: String,
    val coverImgUrl: String,
    val createTime: Long,
    val creator: Creator,
    val description: String,
    val englishTitle: Any,
    val highQuality: Boolean,
    val id: Int,
    val name: String,
    val newImported: Boolean,
    val opRecommend: Boolean,
    val ordered: Boolean,
    val playCount: Int,
    val privacy: Int,
    val shareCount: Int,
    val specialType: Int,
    val status: Int,
    val subscribed: Boolean,
    val subscribedCount: Int,
    val subscribers: List<Any>,
    val tags: List<Any>,
    val titleImage: Int,
    val titleImageUrl: Any,
    val trackCount: Int,
    val trackIds: List<Any>,
    val trackNumberUpdateTime: Long,
    val trackUpdateTime: Long,
    val tracks: List<JsonObject>,
    val updateFrequency: Any,
    val updateTime: Long,
    val userId: Int
)

data class Privilege(
    val cp: Int,
    val cs: Boolean,
    val dl: Int,
    val downloadMaxbr: Int,
    val fee: Int,
    val fl: Int,
    val flag: Int,
    val id: Int,
    val maxbr: Int,
    val payed: Int,
    val pl: Int,
    val playMaxbr: Int,
    val preSell: Boolean,
    val sp: Int,
    val st: Int,
    val subp: Int,
    val toast: Boolean
)

