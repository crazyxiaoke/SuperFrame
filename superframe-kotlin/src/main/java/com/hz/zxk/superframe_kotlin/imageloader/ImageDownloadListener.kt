package com.hz.zxk.superframe_kotlin.imageloader

import android.graphics.Bitmap

interface ImageDownloadListener {
    fun success(bitmap: Bitmap)

    fun error(code: Int, msg: String)
}