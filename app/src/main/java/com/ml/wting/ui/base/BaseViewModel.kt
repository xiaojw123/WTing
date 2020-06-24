package com.ml.wting.ui.base

import androidx.lifecycle.ViewModel
import com.ml.lib_base.net.ApiService
import com.ml.lib_base.net.HttpManager
import com.ml.lib_base.rx.RxCallBack
import com.ml.lib_base.rx.RxHelper
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

open class BaseViewModel:ViewModel(){

    var taskDisposables: CompositeDisposable
    var apiService:ApiService?

    init {
        taskDisposables= CompositeDisposable()
        apiService= HttpManager.getInstance()?.getApiService(ApiService::class.java)

    }


    fun  <T> request(observable:  Observable<T>,callBack: RxCallBack<T> ){
        observable.compose(RxHelper.IO_Transfomer()).subscribe(
            object:Observer<T>{
                override fun onComplete() {
                    callBack.onComplete()
                }

                override fun onSubscribe(d: Disposable?) {
                    taskDisposables.add(d)
                }

                override fun onNext(t: T) {
                    callBack.onResut(t)
                }

                override fun onError(e: Throwable?) {
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