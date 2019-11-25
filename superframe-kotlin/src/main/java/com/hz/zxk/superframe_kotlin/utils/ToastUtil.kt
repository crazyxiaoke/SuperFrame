package com.hz.zxk.superframe_kotlin.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Message
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.hz.zxk.superframe_kotlin.R

/**
@author zhengxiaoke
@date 2019-11-23 20:48
 */
class ToastUtil private constructor(){
    private var toast:Toast?=null

    companion object {
        val instance:ToastUtil by lazy(mode=LazyThreadSafetyMode.SYNCHRONIZED){
            ToastUtil()
        }
    }

    fun showToast(context: Context, msg: String?) {
        showToastToBottom(context, msg)
    }

    fun showToastToCenter(context: Context, msg: String?) {
        showToast(context, msg, Toast.LENGTH_LONG, Gravity.CENTER, 0, 0)
    }

    fun showToastToTop(context: Context, msg: String?) {
        showToast(
            context,
            msg,
            Toast.LENGTH_LONG,
            Gravity.TOP and Gravity.CENTER_HORIZONTAL,
            0,
            60
        )
    }

    fun showToastToBottom(context: Context, msg: String?) {
        showToast(
            context,
            msg,
            Toast.LENGTH_LONG,
            Gravity.BOTTOM and Gravity.CENTER_HORIZONTAL,
            0,
            60
        )
    }


    fun showToast(
        context: Context, msg: String?, duration: Int,
        gravity: Int, x: Int, y: Int
    ) {
        if(toast!=null){
            toast?.cancel()
            toast=null
        }
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.superframe_view_toast, null)
        toast = Toast(context)
        toast?.view = view
        val message = toast?.view?.findViewById(R.id.message) as TextView
        message.text = msg
        toast?.duration = duration
        toast?.setGravity(gravity, x, y)
        toast?.show()
    }
}