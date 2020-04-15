package com.hz.zxk.superhttp_kotlin.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody

class LoggerInterceptor(var isDebug: Boolean) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val startTime = System.currentTimeMillis()
        val response = chain.proceed(request)
        val endTime = System.currentTimeMillis()
        val duration = endTime - startTime
        var responseBody = response.body()
        val body = responseBody?.string()
        responseBody = ResponseBody.create(responseBody?.contentType(), body)
        if(isDebug){
            Log.d("TAG", "Request Start")
            Log.d("TAG", "返回码${response.code()}")
            Log.d("TAG", "$body")
            Log.d("TAG", "Request End:${duration}ms")
        }

        return response.newBuilder().body(responseBody).build()
    }
}