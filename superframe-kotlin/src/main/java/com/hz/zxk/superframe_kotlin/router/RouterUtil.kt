package com.hz.zxk.superframe_kotlin.router

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable
import java.lang.RuntimeException

class RouterUtil private constructor() {
    private var router: IRouter? = null

    companion object {
        val instance: RouterUtil by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RouterUtil()
        }
    }

    fun init(router: IRouter) {
        this.router = router
    }

    fun init() {
        this.init(AliRouter())
    }

    fun pushActivity(context: Context, url: String) {
        checkInit()
        router?.pushActivity(context, url)
    }

    fun pushActivity(context: Context, url: String, bundle: Bundle) {
        checkInit()
        router?.pushActivity(context, url, bundle)
    }

    fun pushActivityForInt(context: Context, url: String, key: String, value: Int) {
        checkInit()
        router?.pushActivityForInt(context, url, key, value)
    }

    fun pushActivityForString(context: Context, url: String, key: String, value: String) {
        checkInit()
        router?.pushActivityForString(context, url, key, value)
    }

    fun pushActivityForBoolean(context: Context, url: String, key: String, value: Boolean) {
        checkInit()
        router?.pushActivityForBoolean(context, url, key, value)
    }

    fun pushActivityForFloat(context: Context, url: String, key: String, value: Float) {
        checkInit()
        router?.pushActivityForFloat(context, url, key, value)
    }

    fun pushActivityForDouble(context: Context, url: String, key: String, value: Double) {
        checkInit()
        router?.pushActivityForDouble(context, url, key, value)
    }

    fun pushActivityForSerializable(
        context: Context,
        url: String,
        key: String,
        value: Serializable
    ) {
        checkInit()
        router?.pushActivityForSerializable(context, url, key, value)
    }

    fun pushActivityForParcelable(context: Context, url: String, key: String, value: Parcelable) {
        checkInit()
        router?.pushActivityForParcelable(context, url, key, value)
    }

    private fun checkInit() {
        if (router == null) {
            throw RuntimeException("请初始化RouterUtil")
        }
    }
}