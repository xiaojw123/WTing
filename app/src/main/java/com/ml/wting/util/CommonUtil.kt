package com.ml.wting.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

object CommonUtil {


    @JvmStatic
    fun inflater(context: Context?, resource: Int): View {



        return inflater(context,resource, null)

    }

    @JvmStatic
    fun inflater(context: Context?, resource: Int,root:ViewGroup?): View {



        return LayoutInflater.from(context).inflate(resource, root,false)

    }


}