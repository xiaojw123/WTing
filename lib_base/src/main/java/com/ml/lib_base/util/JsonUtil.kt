package com.ml.lib_base.util

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken

object JsonUtil {


    private val jsParser = JsonParser()


    val gson by lazy {
        Gson()

    }


    fun String2JS(json: String): JsonObject {
        return jsParser.parse(json).asJsonObject
    }

    fun <T> String2Bean(jle: JsonElement, clss: Class<T>):T{
        return gson.fromJson(jle, clss)

    }


    fun <T> toListBean(json: JsonElement, clss: Class<T>): List<T> {


        val array = json.asJsonArray


        val list = ArrayList<T>()
        for (element in array) {


            val item = gson.fromJson(element, clss)
            list.add(item)


        }


//        val list = gson.fromJson<List<T>>(
//            json,
//            object : TypeToken<List<T>>() {}.type
//        )
        return list


    }


}