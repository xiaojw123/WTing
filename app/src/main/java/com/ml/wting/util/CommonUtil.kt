package com.ml.wting.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View

object CommonUtil {


    @JvmStatic
    fun inflater(context: Context?, resource: Int): View {



        return LayoutInflater.from(context).inflate(resource, null)

    }


}