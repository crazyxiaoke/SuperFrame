package com.hz.zxk.demo.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/**
@author zhengxiaoke
@date 2020/4/15 3:34 PM
 */
class InterceptorOne : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader(
            "app_token",
            "GOG8C/ahU8qQk/tJNepunSjjKxgaKitSDqVPig1viTo9llDHslNIDUMTyohjXsTh3wZR56UZzpmVBwUBDoD6BLzYc9APSRdgWcURuZdfBABSdB8gOLTHXJrRMalMyH1YdUCyhzqJLlQjFopn6KqV1PPcLqNVswzk58W5lzc5vNI="
        )
        builder.addHeader("from", "android11")
        builder.addHeader("version", "1.3.0")

        return chain.proceed(builder.build())
    }
}