package com.hz.zxk.superhttp_kotlin.request

import android.content.Context
import android.os.Debug
import com.hz.zxk.superhttp_kotlin.listener.SuperHttpListener

interface ISuperRequest {
    fun init(context: Context, baseUrl: String)
    fun openDebug(isDebug: Boolean = true)

    fun <T> get(
        url: String,
        param: Map<String, Any>?,
        tag: String?,
        listener: SuperHttpListener<T>?
    )

    fun post(url: String, param: Map<String, Any>?)
    fun <T> post(url: String, param: T?)
    fun delete(url: String, param: Map<String, Any>?)
    fun put(url: String, param: Map<String, Any>?)
}