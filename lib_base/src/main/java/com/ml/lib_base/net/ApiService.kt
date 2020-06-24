package com.ml.lib_base.net

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ApiService {





    @GET("banner")
    fun getBanner():Observable<String>
















}