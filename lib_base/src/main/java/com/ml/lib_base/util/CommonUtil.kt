package com.ml.lib_base.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

object CommonUtil {


    @JvmStatic
    fun inflater(context: Context?, resource: Int): View {


        return inflater(context, resource, null)

    }

    @JvmStatic
    fun inflater(context: Context?, resource: Int, root: ViewGroup?): View {


        return LayoutInflater.from(context).inflate(resource, root, false)


    }

    @JvmStatic
    fun <T : ViewDataBinding> bindInflater(context: Context?, layoutId: Int, root: ViewGroup): T {
        return DataBindingUtil.inflate(
            LayoutInflater.from(context),

            layoutId,

            root,
            false


        )

    }


    fun  toast(context: Context,text:String){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show()
    }


}