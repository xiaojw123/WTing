package com.ml.lib_base.view.adapter

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseRecyclerHolder<VB : ViewDataBinding>(itemView: View) :
    RecyclerView.ViewHolder(itemView),


    View.OnClickListener {

    var mBinding: VB ?= null

    init {
        mBinding=DataBindingUtil.bind(itemView)
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

    }

}