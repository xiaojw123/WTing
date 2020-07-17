package com.ml.wting.repository.model

import android.os.Parcel
import android.os.Parcelable


data class SongItem(
    val id: Long,//id
    val name: String?,//歌名

    val songer: String?,//歌手
    val album: String?,//专辑
    val albumPicUrl: String?


) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeString(songer)
        parcel.writeString(album)
        parcel.writeString(albumPicUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SongItem> {
        override fun createFromParcel(parcel: Parcel): SongItem {
            return SongItem(parcel)
        }

        override fun newArray(size: Int): Array<SongItem?> {
            return arrayOfNulls(size)
        }
    }


}