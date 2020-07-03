package com.ml.lib_base.rx

interface RxCallBack<T> {


    fun onResut(result: T){


    }

    fun onError() {

    }

    fun onComplete() {

    }


    fun onResut(result: List<T>){


    }


}