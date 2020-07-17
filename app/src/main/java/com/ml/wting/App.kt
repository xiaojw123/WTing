package com.ml.wting

import android.app.Application
import android.content.Context

class App : Application() {


    private lateinit var mContext: Context

    private fun getContext(): Context {
        return mContext;
    }


    override fun onCreate() {
        super.onCreate()
        mContext = this


    }

}