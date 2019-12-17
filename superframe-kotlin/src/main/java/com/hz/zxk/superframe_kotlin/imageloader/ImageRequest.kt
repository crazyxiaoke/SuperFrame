package com.hz.zxk.superframe_kotlin.imageloader

import android.content.Context
import android.widget.ImageView

interface ImageRequest {

    fun loadImage(context: Context, url: String, view: ImageView)

    fun loadImage(context: Context, url: String, view: ImageView, option: ImageOption.() -> Unit)

    fun loadCircleImage(context: Context, url: String, view: ImageView)

    fun loadRoundImage(context: Context, url: String, view: ImageView, radius: Int)

    fun downloadImage(context: Context, url: String, listener: ImageDownloadListener?)
}