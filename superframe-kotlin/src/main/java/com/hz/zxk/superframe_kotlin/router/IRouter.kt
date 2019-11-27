package com.hz.zxk.superframe_kotlin.router

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable

interface IRouter {
    fun pushActivity(context: Context?, url: String)

    fun pushActivity(context: Context?, url: String, bundle: Bundle)

    fun pushActivityForInt(context: Context?, url: String, key: String, value: Int)

    fun pushActivityForString(context: Context?, url: String, key: String, value: String)

    fun pushActivityForBoolean(context: Context?, url: String, key: String, value: Boolean)

    fun pushActivityForFloat(context: Context?, url: String, key: String, value: Float)

    fun pushActivityForDouble(context: Context?, url: String, key: String, value: Double)

    fun pushActivityForSerializable(context: Context?, url: String, key: String, value: Serializable)

    fun pushActivityForParcelable(context: Context?, url: String, key: String, value: Parcelable)

}