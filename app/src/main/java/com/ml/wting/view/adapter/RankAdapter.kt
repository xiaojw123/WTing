package com.ml.wting.view.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ml.lib_base.util.CommonUtil
import com.ml.wting.R
import com.ml.wting.util.Constant

import kotlinx.android.synthetic.main.list_item_category.*
import kotlinx.android.synthetic.main.list_item_songer.*
class RankAdapter<T>() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {






    var mItems: List<T>? = null
    var mContext: Context? = null

    var mType = -1


    constructor(viewType: Int, items: List<T>) : this() {

        mItems = items
        mType = viewType
    }

    //mv 歌单、榜单通用

    inner class CommonHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {

        }

    }

    inner class SongerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        mContext = parent.context
        when (mType) {


            Constant.TYPE_SONGER -> {
                return SongerHolder(
                    CommonUtil.inflater(
                        mContext,
                        R.layout.list_item_songer,
                        parent
                    )
                )
            }

            else -> {
                return CommonHolder( CommonUtil.inflater(
                    mContext,
                    R.layout.list_item_category,
                    parent
                ))


            }


        }

    }


    override fun getItemCount(): Int {


        return mItems?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {




    }

}