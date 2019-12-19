package com.hz.zxk.demo

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.hz.zxk.superframe_kotlin.imageloader.ImageLoader
import com.hz.zxk.superframe_kotlin.router.NativeRouter
import com.hz.zxk.superframe_kotlin.router.RouterUtil
import com.hz.zxk.superhttp_kotlin.SuperHttp
import com.hz.zxk.superhttp_kotlin.interceptor.LoggerInterceptor

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        RouterUtil.instance.init(NativeRouter())
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
//        ARouter.init(this)
        SuperHttp.instance.init(this, "http://192.168.0.160:8080/")
        SuperHttp.instance.openDebug()
        ImageLoader.init(this)
    }
}