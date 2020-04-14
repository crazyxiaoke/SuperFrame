package com.hz.zxk.superframe_kotlin.imageloader

import android.graphics.drawable.Drawable

interface ImageDownloadListener {
    fun success(drawable: Drawable)

    fun error(e:Throwable)
}