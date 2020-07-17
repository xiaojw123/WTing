package com.ml.lib_base.rx

import android.text.TextUtils
import android.widget.Toast
import com.ml.lib_base.util.CommonUtil

interface RxCallBack<T> {


    fun onResut(result: T){


    }

    fun onError(msg:String?) {
        if (TextUtils.isEmpty(msg)){
            return
        }

    }

    fun onComplete() {

    }


    fun onResut(result: List<T>){


    }


}