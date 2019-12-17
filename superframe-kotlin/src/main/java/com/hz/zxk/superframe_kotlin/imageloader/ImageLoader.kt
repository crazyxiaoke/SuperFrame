package com.hz.zxk.superframe_kotlin.imageloader

import android.content.Context
import android.view.View

/**
@author zhengxiaoke
@date 2019-12-16 11:34
 */
class ImageLoader {
    private var imageRequest: IImagerRequest? = null;

    companion object {
        private var sContext: Context? = null
        private var sImageRequest: IImagerRequest? = null
        fun init(context: Context) {
            this.sContext = context;
        }

        fun init(context: Context, imageRequest: IImagerRequest) {
            this.init(context)
            this.sImageRequest = imageRequest;
        }
    }

    fun setImageRequest(imageRequest: IImagerRequest): ImageLoader {
        this.imageRequest = imageRequest;
        return this;
    }

    fun loadImage(url:String,view: View){

    }
}