package com.ml.wting.view.adapter

import android.view.View
import com.ml.lib_base.util.CommonUtil
import com.ml.lib_base.view.adapter.BaseRecyclerAdapter
import com.ml.lib_base.view.adapter.BaseRecyclerHolder
import com.ml.wting.R
import com.ml.wting.databinding.ListItemArtistBinding
import com.ml.wting.repository.model.SongItem
import com.ml.wting.ui.home.SongDetailActivity
import com.ml.wting.util.Constant

class ArtistListAdapter(items: List<SongItem>) :
    BaseRecyclerAdapter<SongItem, ArtistListAdapter.Holder, ListItemArtistBinding>(items) {


    inner class Holder(itemView: View) : BaseRecyclerHolder(itemView) {

        override fun onClick(v: View?) {
            CommonUtil.sGotoPage(mContext,SongDetailActivity::class.java,
            Constant.ID,
            v?.tag as Int)
        }
    }

    override fun getItemLayoutRes(): Int {
        return R.layout.list_item_artist
    }

    override fun bindItemView(itemView: View, postion: Int, item: SongItem) {
        mBinding.artistRankTv.setText((postion+1).toString())
        mBinding.item=item
        itemView.tag=item.id
    }


}