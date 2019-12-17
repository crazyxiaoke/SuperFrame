package com.hz.zxk.superframe_kotlin.imageloader

import android.content.Context
import android.view.View

/**
@author zhengxiaoke
@date 2019-12-16 11:09
 */
interface IImagerRequest {
    fun loadImage(context: Context, url: String, view: View)

    fun loadImage(context: Context, url: String, view: View, radius: Int)

}