package com.hz.zxk.superhttp_kotlin.converter

import okhttp3.ResponseBody
import retrofit2.Converter

class MyResponseBodyConverter : Converter<ResponseBody, String> {
    override fun convert(value: ResponseBody): String? {
        return value.string()
    }
}