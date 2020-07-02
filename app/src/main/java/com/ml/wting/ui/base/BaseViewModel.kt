package com.ml.wting.ui.base

import androidx.lifecycle.ViewModel
import com.ml.lib_base.net.ApiService
import com.ml.lib_base.net.HttpManager
import com.ml.lib_base.rx.RxCallBack
import com.ml.lib_base.rx.RxHelper
import com.ml.lib_base.util.APPLOG
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


open class BaseViewModel:ViewModel(){

    var taskDisposables: CompositeDisposable
    var apiService:ApiService?

    init {
        taskDisposables= CompositeDisposable()
        apiService= HttpManager.getInstance()?.getApiService(ApiService::class.java)

    }


    fun  <T> request(observable: Observable<T>, callBack: RxCallBack<T> ){
        observable.compose(RxHelper.IO_Transfomer()).subscribe(
            object: Observer<T> {
                override fun onComplete() {
                    callBack.onComplete()
                }



                override fun onNext(t: T) {
                    APPLOG.printDebug("onNext__"+t)
                    callBack.onResut(t)
                }



                override fun onSubscribe(d: Disposable) {
                    taskDisposables.add(d)
                }

                override fun onError(e: Throwable) {
                    APPLOG.printDebug("onError__"+e)
                    callBack.onError()
                }

            }
        )


    }

    override fun onCleared() {
        taskDisposables.clear()
        super.onCleared()
    }



}