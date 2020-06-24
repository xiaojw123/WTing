package com.ml.lib_base.util

import com.google.gson.JsonObject
import com.google.gson.JsonParser

object JsonUtil {


    private val jsParser = JsonParser()


    fun String2Object(json: String): JsonObject {
        return jsParser.parse(json).asJsonObject
    }




}