package com.hz.zxk.superframe_kotlin.router

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import java.io.Serializable

class NativeRouter : IRouter {
    override fun pushActivity(context: Context?, url: String) {
        val clazz = Class.forName(url)
        Log.d("TAG", "clazz=" + clazz.name)
        val intent = Intent(context, clazz)
        context?.startActivity(intent)
    }

    override fun pushActivity(context: Context?, url: String, bundle: Bundle) {
    }

    override fun pushActivityForInt(context: Context?, url: String, key: String, value: Int) {
    }

    override fun pushActivityForString(context: Context?, url: String, key: String, value: String) {
    }

    override fun pushActivityForBoolean(
        context: Context?,
        url: String,
        key: String,
        value: Boolean
    ) {
    }

    override fun pushActivityForFloat(context: Context?, url: String, key: String, value: Float) {
    }

    override fun pushActivityForDouble(context: Context?, url: String, key: String, value: Double) {
    }

    override fun pushActivityForSerializable(
        context: Context?,
        url: String,
        key: String,
        value: Serializable
    ) {
    }

    override fun pushActivityForParcelable(
        context: Context?,
        url: String,
        key: String,
        value: Parcelable
    ) {
    }
}