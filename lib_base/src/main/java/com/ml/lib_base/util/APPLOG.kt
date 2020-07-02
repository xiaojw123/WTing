package com.ml.lib_base.util

import android.util.Log


object APPLOG {




    const val LOG_TAG="wtlg"

    fun  printDebug(msg:String){
        Log.d(LOG_TAG,msg)
    }
}