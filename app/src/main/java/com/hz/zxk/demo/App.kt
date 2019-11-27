package com.hz.zxk.demo

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.hz.zxk.superframe_kotlin.router.NativeRouter
import com.hz.zxk.superframe_kotlin.router.RouterUtil

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        RouterUtil.instance.init(NativeRouter())
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }
}