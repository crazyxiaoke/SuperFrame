package com.hz.zxk.superhttp_kotlin.config

import okhttp3.Interceptor

/**
@author zhengxiaoke
@date 2020/4/15 3:08 PM
 */
class HttpConfig {
    var baseUrl: String? = null
    var openCache: Boolean = false
    var openDebug: Boolean = false
    var interceptor: Interceptor? = null
    var interceptors: MutableList<Interceptor>? = null
}