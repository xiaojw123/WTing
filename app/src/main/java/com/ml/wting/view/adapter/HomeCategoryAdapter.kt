package com.ml.wting.view.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ml.lib_base.util.DrawableUtil
import com.ml.wting.R
import com.ml.wting.repository.model.CategoryItem
import com.ml.lib_base.util.CommonUtil
import com.ml.wting.ui.base.BaseActivity
import com.ml.wting.ui.home.HomeFragment
import com.ml.wting.ui.home.MVDetailActivity
import com.ml.wting.ui.home.SongDetailActivity
import com.ml.wting.util.Constant

class HomeCategoryAdapter(type: Int, items: List<CategoryItem>) :
    RecyclerView.Adapter<HomeCategoryAdapter.Holder>() {


    var mType: Int

    var mItems: List<CategoryItem>
    lateinit var mContext: Context

    init {
        mItems = items
        mType = type
    }


    inner class Holder(itemview: View) : RecyclerView.ViewHolder(itemview) {


        var categoryImg: ImageView
        var categoryName: TextView
        var categoryAuthor: TextView
        val clickLi = View.OnClickListener {

            val clss = if (mType == HomeFragment.TYPE_M_RECOMMEND)
                MVDetailActivity::class.java
            else
                SongDetailActivity::class.java

            CommonUtil.sGotoPage(
                mContext,
                clss,
                Constant.ID,
                it.tag as Int
            )

        }

        init {

            categoryImg = itemview.findViewById(R.id.item_category_img)

            categoryName = itemview.findViewById(R.id.item_category_title)

            categoryAuthor = itemview.findViewById(R.id.item_category_author)
            itemview.setOnClickListener(clickLi)


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {


        mContext = parent.context

        return Holder(CommonUtil.inflater(mContext, R.layout.list_item_category))

    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = mItems.get(position)

        DrawableUtil.load(
            mContext,
            item.picUrl,
            holder.categoryImg
        )
        holder.categoryName.setText(item.name)
        holder.categoryAuthor.setText(item.artistName)
        holder.itemView.setTag(item.id)


    }
}