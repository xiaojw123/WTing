package com.ml.wting.repository.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ml.lib_base.rx.RxCallBack
import com.ml.lib_base.util.JsonUtil
import com.ml.wting.repository.model.Banner
import com.ml.wting.ui.base.BaseViewModel

class HomeViewModel : BaseViewModel() {


    private var bannerData: MutableLiveData<List<Banner>?>

    init {
        bannerData = MutableLiveData()

    }


    fun getBanner(): LiveData<List<Banner>?> {


        request(apiService?.getBanner()!!, object : RxCallBack<String> {
            override fun onResut(result: String) {
                val resultJS = JsonUtil.String2Object(result)

                val bannerJS = resultJS.get("banners")
                val gson = Gson()
                val banners = gson.fromJson<List<Banner>>(
                    bannerJS,
                    object : TypeToken<List<Banner>>() {}.type
                )
                bannerData.value = banners
            }


        })
        return bannerData

    }


}