package com.hz.zxk.superhttp_kotlin.interceptor

import android.util.Log
import com.orhanobut.logger.Logger
import okhttp3.Interceptor
import okhttp3.Response

class LoggerInterceptor(var isDebug: Boolean) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val startTime = System.currentTimeMillis()
        val response = chain.proceed(request)
        val endTime = System.currentTimeMillis()
        val duration = endTime - startTime
        Log.d("TAG", "拦截器")
//        if (isDebug) {
            Log.d("TAG", "Request Start")
            Log.d("TAG", "返回码${response.code()}")
//            Log.d("TAG", response.body()?.string())
            Log.d("TAG", "Request End:${duration}")
//        }
        return response
    }
}