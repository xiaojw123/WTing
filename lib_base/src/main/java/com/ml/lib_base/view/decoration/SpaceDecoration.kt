package com.ml.lib_base.view.decoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ml.lib_base.util.DXUtil

class SpaceDecoration(dpValue: Int, context: Context) : RecyclerView.ItemDecoration() {




    constructor(dpValue: Int,hasBound:Boolean,headerSize:Int, context: Context):this(dpValue,context){
        mHasBound=hasBound
        mHeadSize=headerSize
    }



    var mDpValue: Int
    var mHasBound:Boolean=false
    var mHeadSize:Int=0

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

            var positon= parent.getChildAdapterPosition(view)
            if (positon<mHeadSize){
                if (mHasBound){
                    outRect.left=mDpValue
                }
                outRect.bottom=mDpValue
                return

            }
            val colum = lm.spanCount

            if (mHasBound){
                positon-=mHeadSize
                if (positon/colum==0){
                    outRect.top=mDpValue
                }
                if (positon%colum==0){
                    outRect.left=mDpValue
                }
                outRect.right=mDpValue
                outRect.bottom=mDpValue
                return
            }

            if (positon % colum > 0) {
                outRect.left = mDpValue
            }
            outRect.bottom = mDpValue
        }


    }


}