package com.ml.wting.repository.model

data class MVDetail(
    val artistId: Int,
    val artistName: String,
    val artists: List<Artist>,
    val briefDesc: String,
    val brs: Brs,
    val commentCount: Int,
    val commentThreadId: String,
    val cover: String,
    val coverId: Long,
    val desc: String,
    val duration: Int,
    val id: Int,
    val isReward: Boolean,
    val likeCount: Int,
    val nType: Int,
    val name: String,
    val playCount: Int,
    val publishTime: String,
    val shareCount: Int,
    val subCount: Int
)

data class Artist(
    val id: Int,
    val name: String
)

data class Brs(
    val `1080`: String,
    val `240`: String,
    val `480`: String,
    val `720`: String
)