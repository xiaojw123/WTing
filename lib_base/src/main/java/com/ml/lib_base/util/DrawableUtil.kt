package com.ml.lib_base.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ml.lib_base.drawable.RoundCorner

object DrawableUtil {




    fun  load(context:Context?,url:String?,target:ImageView){
        if (context==null||url==null){
            return
        }

        Glide.with(context).load(url).into(target)
    }

    fun  loadRound(context: Context?,url: String?,corner:Float,target: ImageView){

        if (context==null||url==null){
            return
        }

        Glide.with(context).load(url).apply(RequestOptions.bitmapTransform(RoundCorner(context,corner))).into(target)

    }

}