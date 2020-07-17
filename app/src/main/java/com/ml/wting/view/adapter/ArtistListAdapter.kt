package com.ml.wting.view.adapter

import android.view.View
import com.ml.lib_base.util.APPLOG
import com.ml.lib_base.util.CommonUtil
import com.ml.lib_base.view.adapter.BaseRecyclerAdapter
import com.ml.lib_base.view.adapter.BaseRecyclerHolder
import com.ml.wting.R
import com.ml.wting.databinding.ListItemArtistBinding
import com.ml.wting.repository.model.SongItem
import com.ml.wting.ui.home.SongDetailActivity
import com.ml.wting.util.Constant

class ArtistListAdapter(items: List<SongItem>) :
    BaseRecyclerAdapter<SongItem, ArtistListAdapter.Holder>(items) {


 open  inner class Holder(itemView: View) : BaseRecyclerHolder<ListItemArtistBinding>(itemView) {

        override fun onClick(v: View?) {
            CommonUtil.sGotoPage(
                v!!.context, SongDetailActivity::class.java,
                Constant.ID,
                v.tag as Long
            )

        }
    }

    override fun getItemLayoutRes(): Int {

        return R.layout.list_item_artist
    }


    override fun bindItemView(holder: Holder, postion: Int, item: SongItem) {
        if (holder.mBinding != null) {
            val rankNum = postion + 1
            holder.mBinding!!.artistRankTv.text = rankNum.toString()
            holder.mBinding!!.item = item
            holder.itemView.tag = item.id
        }

    }


}