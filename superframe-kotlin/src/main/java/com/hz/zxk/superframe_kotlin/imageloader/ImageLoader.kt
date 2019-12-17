package com.hz.zxk.superframe_kotlin.imageloader

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.ImageView
import java.lang.NullPointerException
import java.lang.ref.WeakReference

class ImageLoader private constructor() {

    companion object {
        var context: WeakReference<Context>? = null
        var imageRequest: ImageRequest? = null
        fun init(context: Context) {
            this.init(context, GlideImageRequest())
        }

        fun init(context: Context, imageRequest: ImageRequest) {
            this.context = WeakReference(context)
            this.imageRequest = imageRequest
        }

        val instance: ImageLoader by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            ImageLoader();
        }
    }

    fun loadImage(url: String, view: ImageView) {
        checkInit()
        imageRequest!!.loadImage(context?.get()!!, url, view)
    }

    fun loadImage(url: String, view: ImageView, option: ImageOption.() -> Unit) {
        checkInit()
        imageRequest!!.loadImage(context?.get()!!, url, view, option)
    }

    fun loadCircleImage(url: String, view: ImageView) {
        checkInit()
        imageRequest!!.loadCircleImage(context?.get()!!, url, view)
    }

    fun loadRoundImage(url: String, view: ImageView, radius: Int) {
        checkInit()
        imageRequest!!.loadRoundImage(context?.get()!!, url, view, radius);
    }

    fun downloadImage(url: String, listener: ImageDownloadListener?) {
        imageRequest!!.downloadImage(context?.get()!!, url, listener)
    }

    private fun checkInit() {
        if (context == null || imageRequest == null) {
            throw NullPointerException("请在Application中初始化ImageLoder")
        }
    }


}