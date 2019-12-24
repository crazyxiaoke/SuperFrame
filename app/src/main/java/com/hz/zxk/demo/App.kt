package com.hz.zxk.demo

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.hz.zxk.superframe_kotlin.imageloader.ImageLoader
import com.hz.zxk.superframe_kotlin.router.NativeRouter
import com.hz.zxk.superframe_kotlin.router.RouterUtil
import com.hz.zxk.superhttp_kotlin.SuperHttp
import com.hz.zxk.superhttp_kotlin.interceptor.LoggerInterceptor
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.*
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader

class App : Application() {
    companion object {
        init {
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
                ClassicsHeader(context)//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
            //设置全局的Footer构建器
            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
                //指定为经典Footer，默认是 BallPulseFooter
                ClassicsFooter(context).setDrawableSize(20f)
            }
        }
    }

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