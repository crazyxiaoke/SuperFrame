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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pushActivityForInt(context: Context?, url: String, key: String, value: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pushActivityForString(context: Context?, url: String, key: String, value: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pushActivityForBoolean(
        context: Context?,
        url: String,
        key: String,
        value: Boolean
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pushActivityForFloat(context: Context?, url: String, key: String, value: Float) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pushActivityForDouble(context: Context?, url: String, key: String, value: Double) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pushActivityForSerializable(
        context: Context?,
        url: String,
        key: String,
        value: Serializable
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pushActivityForParcelable(
        context: Context?,
        url: String,
        key: String,
        value: Parcelable
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}