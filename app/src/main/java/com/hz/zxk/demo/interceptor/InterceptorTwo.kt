package com.hz.zxk.demo.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/**
@author zhengxiaoke
@date 2020/4/15 3:34 PM
 */
class InterceptorTwo : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d("TAG", "我是第二个拦截器")
        return chain.proceed(chain.request())
    }
}