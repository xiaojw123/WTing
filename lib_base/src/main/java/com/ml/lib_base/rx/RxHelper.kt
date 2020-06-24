package com.ml.lib_base.rx

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.schedulers.Schedulers

object RxHelper {





    fun <T> IO_Transfomer():ObservableTransformer<T,T>{

        return ObservableTransformer{

                upstream->
                upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

        }



    }


}