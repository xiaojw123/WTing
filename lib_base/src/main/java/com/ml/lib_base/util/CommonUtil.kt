package com.ml.lib_base.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
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
    fun <B : ViewDataBinding> bindinflater(context: Context, layoutId: Int, root: ViewGroup): B {
        return DataBindingUtil.inflate(
            LayoutInflater.from(context),
            layoutId,
            root,
            false


        )
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


    fun toast(context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    //    param key,value..
    fun <T:Any> sGotoPage(context: Context, clss: Class<T>, vararg params: Any) {
        val intent = Intent(context, clss)
        var isSwitch = true
        var key: String? = null
        for (param in params) {
            if (isSwitch) {
                isSwitch = false
                if (param is String) {
                    key = param
                } else {//参数定义出错放弃传参
                    break
                }
            } else {
                isSwitch = true
                if (key == null) {//参数定义出错放弃传参
                    break
                }
                if (param is Number) {
                    intent.putExtra(key, param)
                }
                if (param is CharSequence) {
                    intent.putExtra(key, param)
                }
                if (param is Boolean) {
                    intent.putExtra(key, param)
                }




                key = null
            }


        }
        context.startActivity(intent)

    }

    fun <T> gotoPage(context: Context, clss: Class<T>, data: Bundle?) {
        val intent = Intent(context, clss)
        if (data != null) {
            intent.putExtras(data)
        }

        context.startActivity(intent)
    }

    fun <T> gotoPage(context: Context, clss: Class<T>) {
        gotoPage(context, clss, null)
    }


}