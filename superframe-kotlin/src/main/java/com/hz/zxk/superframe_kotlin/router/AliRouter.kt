package com.hz.zxk.superframe_kotlin.router

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter
import java.io.Serializable

class AliRouter : IRouter {

    override fun pushActivity(context: Context?, url: String, bundle: Bundle) {
        val postcard = ARouter.getInstance().build(url)
            .with(bundle)
        navigation(context, postcard)
    }

    override fun pushActivity(context: Context?, url: String) {
        val postcard = ARouter.getInstance().build(url)
        navigation(context, postcard)
    }

    override fun pushActivityForInt(context: Context?, url: String, key: String, value: Int) {
        val postcard = ARouter.getInstance().build(url)
            .withInt(key, value)
        navigation(context, postcard)
    }

    override fun pushActivityForString(context: Context?, url: String, key: String, value: String) {
        val postcard = ARouter.getInstance().build(url)
            .withString(key, value)
        navigation(context, postcard)
    }

    override fun pushActivityForBoolean(
        context: Context?,
        url: String,
        key: String,
        value: Boolean
    ) {
        val postcard = ARouter.getInstance().build(url)
            .withBoolean(key, value)
        navigation(context, postcard)
    }

    override fun pushActivityForFloat(context: Context?, url: String, key: String, value: Float) {
        val postcard = ARouter.getInstance().build(url)
            .withFloat(key, value)
        navigation(context, postcard)
    }

    override fun pushActivityForDouble(context: Context?, url: String, key: String, value: Double) {
        val postcard = ARouter.getInstance().build(url)
            .withDouble(key, value)
        navigation(context, postcard)
    }

    override fun pushActivityForSerializable(
        context: Context?,
        url: String,
        key: String,
        value: Serializable
    ) {
        val postcard = ARouter.getInstance().build(url)
            .withSerializable(key, value)
        navigation(context, postcard)
    }

    override fun pushActivityForParcelable(
        context: Context?,
        url: String,
        key: String,
        value: Parcelable
    ) {
        val postcard = ARouter.getInstance().build(url)
            .withParcelable(key, value)
        navigation(context, postcard)
    }

    private fun navigation(context: Context?, postcard: Postcard) {
        if (context != null) {
            postcard.navigation(context)
        } else {
            postcard.navigation()
        }
    }
}