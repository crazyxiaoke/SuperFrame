package com.hz.zxk.superutil_kotlin.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build

object NetWorkUtil {
    /**
     * 判断网络是否连接正常
     */
    fun isConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm.activeNetwork.also {
                if (null != it) {
                    val nc = cm.getNetworkCapabilities(it)
                    if (null != nc) {
                        if (nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                            return true
                        } else if (nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                            return true
                        }
                    }
                }
            }
            return false
        } else {
            (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo.also {
                if (null != it && it.isConnected) {
                    if (it.state == NetworkInfo.State.CONNECTED) {
                        return true
                    }
                }
                return false
            }
        }
    }
}