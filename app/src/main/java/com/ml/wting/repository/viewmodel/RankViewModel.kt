package com.ml.wting.repository.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ml.lib_base.rx.RxCallBack
import com.ml.wting.repository.model.BaseResult
import com.ml.wting.repository.model.MVEntity
import com.ml.wting.ui.base.BaseViewModel

class RankViewModel : BaseViewModel() {


    var mMVLiveData: MutableLiveData<List<MVEntity>>


//    ObservableField


    init {

        mMVLiveData = MutableLiveData()

    }


    fun getRanMV(): LiveData<List<MVEntity>> {
        requestList(apiService!!.getRankNewMV(), object : RxCallBack<MVEntity> {
            override fun onResut(result: List<MVEntity>) {
                mMVLiveData.value = result
            }


        })














        return mMVLiveData


    }


}