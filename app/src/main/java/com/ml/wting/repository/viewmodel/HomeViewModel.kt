package com.ml.wting.repository.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.ml.lib_base.rx.RxCallBack
import com.ml.lib_base.util.APPLOG
import com.ml.lib_base.util.JsonUtil
import com.ml.wting.repository.model.BannerItem
import com.ml.wting.repository.model.CategoryItem
import com.ml.wting.ui.base.BaseViewModel

class HomeViewModel : BaseViewModel() {


    private var bannerData: MutableLiveData<List<BannerItem>?>
    private var newSongData: MutableLiveData<List<CategoryItem>?>
    private var recommendMvData: MutableLiveData<List<CategoryItem>?>

    init {
        bannerData = MutableLiveData()
        newSongData = MutableLiveData()
        recommendMvData = MutableLiveData()

    }


    fun getBanner(): LiveData<List<BannerItem>?> {


        request(apiService?.getBanner()!!, object : RxCallBack<JsonObject> {
            override fun onResut(result: JsonObject) {
                APPLOG.printDebug("banner Result——" + result)
                val bannerJS = result.get("banners")
                val gson = Gson()
                val banners = gson.fromJson<List<BannerItem>>(
                    bannerJS,
                    object : TypeToken<List<BannerItem>>() {}.type
                )
                bannerData.value = banners
                bannerData.value
            }


        })
        return bannerData

    }

    fun getNewSong(): LiveData<List<CategoryItem>?> {

        request(apiService?.getNewSong()!!, object : RxCallBack<JsonObject> {
            override fun onResut(result: JsonObject) {

                val songs = JsonUtil.toListBean(result.get("result"),CategoryItem::class.java)
                newSongData.value = songs
            }


        })

        return newSongData


    }

    fun getRecommnedMV(): LiveData<List<CategoryItem>?> {

        request(apiService?.getRecommendMV()!!, object : RxCallBack<JsonObject> {
            override fun onResut(result: JsonObject) {

                val songs = JsonUtil.toListBean(result.get("result"),CategoryItem::class.java)
                recommendMvData.value = songs

            }


        })

        return recommendMvData


    }


}