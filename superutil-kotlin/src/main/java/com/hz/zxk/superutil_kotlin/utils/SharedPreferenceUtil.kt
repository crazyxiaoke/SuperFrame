package com.hz.zxk.superframe_kotlin.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceUtil private constructor() {
    private var sharedPreferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null
    private var name: String? = null
    private var context: Context? = null

    companion object {
        val instance: SharedPreferenceUtil by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            SharedPreferenceUtil()
        }
    }

    @SuppressLint("CommitPrefEdits")
    fun init(
        context: Context,
        name: String = "mySharedPreference",
        mode: Int = Context.MODE_PRIVATE
    ) {
        this.context = context
        sharedPreferences = context.getSharedPreferences(name, mode)
        editor = sharedPreferences?.edit()
    }

    fun putValue(key: String, value: Int): SharedPreferenceUtil {
        editor?.putInt(key, value)
        return this
    }

    fun putValue(key: String, value: String): SharedPreferenceUtil {
        editor?.putString(key, value)
        return this
    }

    fun putValue(key: String, value: Boolean): SharedPreferenceUtil {
        editor?.putBoolean(key, value)
        return this
    }

    fun putValue(key: String, value: Float): SharedPreferenceUtil {
        editor?.putFloat(key, value)
        return this
    }

    fun putValue(key: String, value: Long): SharedPreferenceUtil {
        editor?.putLong(key, value)
        return this
    }

    fun putValue(key: String, value: Set<String>): SharedPreferenceUtil {
        editor?.putStringSet(key, value)
        return this
    }

    fun getString(key: String, defValue: String = ""): String? {
        return sharedPreferences?.getString(key, defValue)
    }


    fun getInt(key: String, defValue: Int = -1): Int? {
        return sharedPreferences?.getInt(key, defValue)
    }


    fun getBoolean(key: String, defValue: Boolean = false): Boolean? {
        return sharedPreferences?.getBoolean(key, defValue)
    }


    fun getLong(key: String, defValue: Long = -1): Long? {
        return sharedPreferences?.getLong(key, defValue)
    }


    fun getFloat(key: String, defValue: Float = 0f): Float? {
        return sharedPreferences?.getFloat(key, defValue)
    }


    fun getSet(key: String, defValue: Set<String>? = null): Set<String>? {
        return sharedPreferences?.getStringSet(key, defValue)
    }

    /**
     *  删除某个key
     */
    fun remove(key: String) {
        editor?.remove(key)
    }

    /**
     * 清空
     */
    fun clear() {
        editor?.clear()
    }


    /**
     *  异步提交
     */
    fun apply() {
        editor?.apply()
    }

    /**
     * 同步提交
     */
    fun commit() {
        editor?.commit()
    }


}