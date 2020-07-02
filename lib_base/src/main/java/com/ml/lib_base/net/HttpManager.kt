package com.ml.lib_base.net

import android.util.ArrayMap
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.internal.schedulers.RxThreadFactory
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import java.security.acl.AclNotFoundException
import java.util.concurrent.ConcurrentHashMap

class HttpManager private constructor() {


    companion object {
        private const val BASE_URL = "http://neteasecloudmusicapi.zhaoboy.com/"
        private val hm: HttpManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            HttpManager()
        }

        @JvmStatic
        fun getInstance(): HttpManager? {
            return hm
        }

    }


    private var apiCache: ConcurrentHashMap<String, Any>;


    init {
        apiCache = ConcurrentHashMap();
    }


    fun <T> getApiService(claz: Class<T>): T {

        val api = apiCache.get(claz.name)
        if (api == null) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
            val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
            val builder = Retrofit.Builder()
            builder.client(client)
            builder.baseUrl(BASE_URL)
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            builder.addConverterFactory(GsonConverterFactory.create())
            val retrofit = builder.build()
            val apiInteface = retrofit.create(claz)
            apiCache.put(claz.name, apiInteface as Any)
            return apiInteface
        } else {
            return api as T;
        }

    }


}