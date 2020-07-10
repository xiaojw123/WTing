package com.ml.wting.repository.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import com.ml.lib_base.rx.RxCallBack
import com.ml.lib_base.util.JsonUtil
import com.ml.wting.repository.model.MVDetail
import com.ml.wting.ui.base.BaseViewModel

class MVDetailViewModel : BaseViewModel() {


    val mDetailLiveData by lazy {

        MutableLiveData<MVDetail>()

    }


    fun getMVDetail(mvid: Int): LiveData<MVDetail> {


        request(apiService.getMVDetail(mvid),
            object : RxCallBack<JsonObject> {
                override fun onResut(result: JsonObject) {
                    val dataJS = result.getAsJsonObject("data")
                    val detail = JsonUtil.String2Bean(dataJS, MVDetail::class.java)
                    mDetailLiveData.value = detail
                }
            })


        return mDetailLiveData
    }


}