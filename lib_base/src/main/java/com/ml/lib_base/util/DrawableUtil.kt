package com.ml.lib_base.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object DrawableUtil {




    fun  load(context:Context,url:String,target:ImageView){
        Glide.with(context).load(url).into(target)
    }

}