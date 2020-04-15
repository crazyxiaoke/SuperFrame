package com.hz.zxk.superview_kotlin.util

import android.view.View

/**
@author zhengxiaoke
@date 2020/4/14 4:19 PM
 */

fun View.dp2px(value: Float): Int {
    val scale = resources.displayMetrics.density
    return (value * scale + 0.5f).toInt()
}