package com.hz.zxk.superhttp_kotlin.request

import android.content.Context
import com.hz.zxk.superhttp_kotlin.config.HttpConfig
import com.hz.zxk.superhttp_kotlin.listener.SuperCallback

interface ISuperRequest {
    fun init(context: Context, baseUrl: String)
    fun init(context: Context, config: HttpConfig.() -> Unit)
    fun openDebug(isDebug: Boolean = true)

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

    fun <T, D> post(
        url: String,
        param: T?,
        tag: String?,
        listener: SuperCallback<D>?
    )

    fun <T> delete(
        url: String,
        param: Map<String, Any>?,
        tag: String?,
        listener: SuperCallback<T>?
    )

    fun <T, D> put(
        url: String,
        param: T?,
        tag: String?,
        listener: SuperCallback<D>?
    )
}