package com.hz.zxk.superframe_kotlin.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable

class StartActivityUtil {
    companion object {
        private const val KEY_INT = "int"
        private const val KEY_BOOLEAN = "boolean"
        private const val KEY_STRING = "string"
        private const val KEY_FLOAT = "float"
        private const val KEY_DOUBLE = "double"
        private const val KEY_SERIALIZABLE = "serializable"
        private const val KEY_PARCELABLE = "parcelable"

        fun pushActivity(context: Context, activity: Class<Any>, bundle: Bundle?) {
            val intent = Intent(context, activity)
            if (bundle != null) {
                intent.putExtras(bundle)
            }
            context.startActivity(intent)
        }

        fun pushActivity(context: Context, activity: Class<Any>) {
            pushActivity(context, activity, null)
        }

        fun pushActivityForInt(
            context: Context,
            activity: Class<Any>,
            value: Int
        ) {
            val bundle = Bundle()
            bundle.putInt(KEY_INT, value)
            pushActivity(context, activity, bundle)
        }

        fun pushActivityForString(
            context: Context,
            activity: Class<Any>,
            value: String
        ) {
            val bundle = Bundle()
            bundle.putString(KEY_STRING, value)
            pushActivity(context, activity, bundle)
        }

        fun pushActivityForBoolean(
            context: Context,
            activity: Class<Any>,
            value: Boolean
        ) {
            val bundle = Bundle()
            bundle.putBoolean(KEY_BOOLEAN, value)
            pushActivity(context, activity, bundle)
        }

        fun pushActivityForFloat(
            context: Context,
            activity: Class<Any>,
            value: Float
        ) {
            val bundle = Bundle()
            bundle.putFloat(KEY_FLOAT, value)
            pushActivity(context, activity, bundle)
        }

        fun pushActivityForDouble(
            context: Context,
            activity: Class<Any>,
            value: Double
        ) {
            val bundle = Bundle()
            bundle.putDouble(KEY_DOUBLE, value)
            pushActivity(context, activity, bundle)
        }

        fun pushActivityForSerializable(
            context: Context,
            activity: Class<Any>,
            value: Serializable
        ) {
            val bundle = Bundle();
            bundle.putSerializable(KEY_SERIALIZABLE, value)
            pushActivity(context, activity, bundle)
        }

        fun pushActivityForParcelable(
            context: Context,
            activity: Class<Any>,
            value: Parcelable
        ) {
            val bundle = Bundle()
            bundle.putParcelable(KEY_PARCELABLE, value)
            pushActivity(context, activity, bundle)
        }

        fun getInt(intent: Intent, defValue: Int = 0): Int {
            return intent.getIntExtra(KEY_INT, defValue)
        }

        fun getString(intent: Intent): String? {
            return intent.getStringExtra(KEY_STRING)
        }

        fun getBoolean(intent: Intent, defValue: Boolean = false): Boolean {
            return intent.getBooleanExtra(KEY_BOOLEAN, defValue)
        }

        fun getFloat(intent: Intent, defValue: Float = 0.0f): Float {
            return intent.getFloatExtra(KEY_FLOAT, defValue)
        }

        fun getDouble(intent: Intent, defvalue: Double = 0.0): Double {
            return intent.getDoubleExtra(KEY_DOUBLE, defvalue)
        }

        fun getSerializable(intent: Intent): Serializable? {
            return intent.getSerializableExtra(KEY_SERIALIZABLE)
        }

        fun getParcelable(intent: Intent): Parcelable? {
            return intent.getParcelableExtra(KEY_PARCELABLE)
        }
    }
}