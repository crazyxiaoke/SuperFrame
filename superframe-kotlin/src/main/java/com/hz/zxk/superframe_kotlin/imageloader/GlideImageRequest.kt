package com.hz.zxk.superframe_kotlin.imageloader

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.hz.zxk.superframe_kotlin.imageloader.transform.CenterCropRoundCornerTransform

class GlideImageRequest : ImageRequest {
    override fun loadImage(context: Context, url: String, view: ImageView) {
        Glide.with(context).load(url).into(view)
    }

    @SuppressLint("CheckResult")
    override fun loadImage(
        context: Context,
        url: String,
        view: ImageView,
        option: ImageOption.() -> Unit
    ) {
        val imageOption = ImageOption()
        option.invoke(imageOption)
        val requestManager = Glide.with(context);
        val builder = requestManager.load(url)
        val requestOptions = RequestOptions()
        if (imageOption.placeHolder != 0) {
            requestOptions.placeholder(imageOption.placeHolder)
        }
        if (imageOption.errorHolder != 0) {
            requestOptions.error(imageOption.errorHolder)
        }
        if (imageOption.radius != 0) {
            requestOptions.transform(CenterCropRoundCornerTransform(imageOption.radius))
        }
        builder.apply(requestOptions).into(view)
    }

    override fun loadCircleImage(context: Context, url: String, view: ImageView) {
        loadImage(context, url, view) {
            radius = 360
        }
    }


    override fun loadRoundImage(context: Context, url: String, view: ImageView, r: Int) {
        loadImage(context, url, view) {
            radius = r
        }
    }

    @SuppressLint("CheckResult")
    override fun downloadImage(
        context: Context,
        url: String,
        listener: ImageDownloadListener?
    ) {

        Glide.with(context).load(url)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    e?.let { listener?.error(e) }
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    resource?.let { listener?.success(resource) }
                    return true
                }
            })
    }
}