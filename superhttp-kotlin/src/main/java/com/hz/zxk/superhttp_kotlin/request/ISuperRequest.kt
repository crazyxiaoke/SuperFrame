package com.hz.zxk.superhttp_kotlin.request

import android.content.Context
import com.hz.zxk.superhttp_kotlin.config.HttpConfig
import com.hz.zxk.superhttp_kotlin.listener.SuperCallback

interface ISuperRequest {
    fun init(context: Context, baseUrl: String)
    fun init(context: Context, config: HttpConfig.() -> Unit)

    fun <T> get(
        url: String,
        param: Map<String, Any>?,
        tag: String?,
        listener: SuperCallback<T>?
    )

    fun <T> post(
        url: String,
        param: Map<String, Any>?,
        tag: String?,
        listener: SuperCallback<T>?
    )

    fun <T> post(
        url: String,
        param: Any?,
        tag: String?,
        listener: SuperCallback<T>?
    )

    fun <T> delete(
        url: String,
        param: Map<String, Any>?,
        tag: String?,
        listener: SuperCallback<T>?
    )

    fun <T> put(
        url: String,
        param: Any?,
        tag: String?,
        listener: SuperCallback<T>?
    )
}