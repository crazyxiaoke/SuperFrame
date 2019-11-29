package com.hz.zxk.superhttp_kotlin.converter

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.NullPointerException
import java.lang.reflect.Type

class MyGsonFactory(val gson: Gson) : Converter.Factory() {

    companion object {
        fun create(): MyGsonFactory {
            return create(GsonBuilder().setLenient().create())
        }

        fun create(gson: Gson): MyGsonFactory {
            return MyGsonFactory(gson)
        }
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        return MyResponseBodyConverter()
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<Annotation>,
        methodAnnotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody>? {
        val adapter = gson.getAdapter(TypeToken.get(type))
        return MyRequestBodyConverter(gson, adapter)
    }

}