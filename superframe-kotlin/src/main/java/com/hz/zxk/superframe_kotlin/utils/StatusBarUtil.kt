package com.hz.zxk.superframe_kotlin.utils

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.core.view.children

/**
@author zhengxiaoke
@date 2019-11-20 10:52
 */
object StatusBarUtil {

    /**
     * 设置透明状态栏
     */
    fun transparent(activity: Activity) {
        transparentStatusBar(activity)
        setRootView(activity)
    }

    /**
     * 设置状态栏颜色
     */
    fun setStatusBarColor(activity: Activity, color: Int) {
        activity.window.statusBarColor = color
    }

    /**
     * 设置状态栏字体亮色
     */
    fun setStatusTextLight(activity: Activity, light: Boolean) {
        Log.d("TAG", "亮色文字1")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (light) {
                activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            }
        }
    }


    /**
     * 设置透明状态栏
     */
    private fun transparentStatusBar(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //android5.0及以上
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//            activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            activity.window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN and View.SYSTEM_UI_FLAG_LAYOUT_STABLE

            activity.window.statusBarColor = Color.TRANSPARENT
        } else {
            //android5.0以下
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }

    private fun setRootView(activity: Activity) {
        val root: ViewGroup = activity.findViewById(android.R.id.content)
        for (i in 0..root.childCount) {
            val childView = root.getChildAt(i)
            if (childView is ViewGroup) {
                childView.fitsSystemWindows = true
                childView.clipToPadding = true
            }

        }
    }


}