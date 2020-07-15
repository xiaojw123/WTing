package com.ml.lib_base.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class BaseRecyclerHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener {

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

    }

}