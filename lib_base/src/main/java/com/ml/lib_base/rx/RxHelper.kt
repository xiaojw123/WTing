package com.ml.lib_base.rx

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


object RxHelper {





    fun <T> IO_Transfomer(): ObservableTransformer<T, T> {

        return ObservableTransformer{

                upstream->
                upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

        }



    }


}