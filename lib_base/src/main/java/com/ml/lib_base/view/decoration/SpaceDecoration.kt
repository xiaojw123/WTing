package com.ml.lib_base.view.decoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ml.lib_base.util.DXUtil

class SpaceDecoration(dpValue: Int, context: Context) : RecyclerView.ItemDecoration() {


    var mDpValue: Int

    init {
        mDpValue = DXUtil.dip2px(context, dpValue.toFloat())

    }


    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        val lm = parent.layoutManager

        if (lm is GridLayoutManager) {

            val positon = parent.indexOfChild(view)
            val colum = lm.spanCount

            if (positon % colum > 0) {
                outRect.left = mDpValue
            }
            outRect.bottom = mDpValue
        }


    }


}