package com.hz.zxk.demo.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/**
@author zhengxiaoke
@date 2020/4/15 3:28 PM
 */
class LoggerInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d("TAG", "我是自定义的拦截器")
        val request = chain.request()
        Log.d("TAG", "url=" + request.url().toString())
        return chain.proceed(request)
    }
}