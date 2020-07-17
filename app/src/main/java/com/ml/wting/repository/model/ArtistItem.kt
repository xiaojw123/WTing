package com.ml.wting.repository.model

data class ArtistItem(
    val albumSize: Int,
    val alias: List<Any>,
    val briefDesc: String,
    val id: Long,
    val img1v1Id: Long,
    val img1v1Id_str: String,
    val img1v1Url: String,
    val lastRank: Int,
    val musicSize: Int,
    val name: String,
    val picId: Long,
    val picId_str: String,
    val picUrl: String,
    val score: Int,
    val topicPerson: Int,
    val trans: String
)