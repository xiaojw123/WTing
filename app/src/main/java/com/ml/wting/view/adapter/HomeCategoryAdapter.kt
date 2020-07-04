package com.ml.wting.view.adapter

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

class HomeCategoryAdapter(items: List<CategoryItem>) :
    RecyclerView.Adapter<HomeCategoryAdapter.Holder>() {




    var mItems: List<CategoryItem>
    lateinit var mContext: Context

    init {
        mItems = items
    }


    inner class Holder(itemview: View) : RecyclerView.ViewHolder(itemview) {


        var categoryImg: ImageView
        var categoryName: TextView
        var categoryAuthor: TextView

        init {

            categoryImg = itemview.findViewById(R.id.item_category_img)

            categoryName = itemview.findViewById(R.id.item_category_title)

            categoryAuthor = itemview.findViewById(R.id.item_category_author)


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


    }
}