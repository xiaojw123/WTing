package com.ml.wting.view.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ml.lib_base.util.DrawableUtil
import com.ml.wting.repository.model.BannerItem
import com.youth.banner.adapter.BannerAdapter

class HomeBannerAdapter(datas: List<BannerItem>?) :
    BannerAdapter<BannerItem, HomeBannerAdapter.Holder>(datas) {
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)

    lateinit var mContext: Context
    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): Holder {
        mContext = parent!!.context
        val imageView = ImageView(mContext)
        imageView.setLayoutParams(
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP)
        return Holder(imageView)
    }

    override fun onBindView(holder: Holder?, data: BannerItem?, position: Int, size: Int) {

        if (data != null) {
            DrawableUtil.load(mContext, data.imageUrl, holder?.itemView as ImageView)
        }
    }
}