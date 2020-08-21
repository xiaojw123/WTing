package com.ml.wting.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.VideoView

class CustomVideoView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : VideoView(context,attrs,defStyleAttr) {


    constructor(context: Context, atts: AttributeSet?) : this(context,atts,0)


    constructor(context: Context) : this(context, null)


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val width = View.getDefaultSize(getWidth(), widthMeasureSpec)
        val height = View.getDefaultSize(getHeight(), heightMeasureSpec)

        setMeasuredDimension(width, height)

    }


}