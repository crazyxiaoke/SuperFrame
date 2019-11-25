package com.hz.zxk.superframe_kotlin.extend

import android.content.Context
import android.util.TypedValue

/**
 * context的扩展函数
@author zhengxiaoke
@date 2019-11-23 21:31
 */

/**
 * dp转px
 */
fun Context.dp2px(value: Float): Float {
    val scale = resources.displayMetrics.density
    return value * scale + 0.5f
}

/**
 * dp转px
 */
fun Context.dp2px(value: Int): Int {
    val scale = resources.displayMetrics.density
    return (value * scale + 0.5f).toInt()
}

/**
 * sp转px
 */
fun Context.sp2px(value: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, resources.displayMetrics)
}

/**
 * 屏幕宽度
 */
fun Context.screenWidth(): Int {
    return resources.displayMetrics.widthPixels
}

/**
 * 屏幕高度
 */
fun Context.screenHeight(): Int {
    return resources.displayMetrics.heightPixels
}

/**
 * 获取状态栏高度
 */
fun Context.getStatusHeight(): Int {
    val clazz = Class.forName("com.android.internal.R\$dimen")
    val obj = clazz.newInstance()
    val field = clazz.getField("status_bar_height")
    val x = field.getInt(obj)
    return resources.getDimensionPixelSize(x)
}

