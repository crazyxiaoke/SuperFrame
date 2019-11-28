package com.hz.zxk.superhttp_kotlin.base

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hz.zxk.superhttp_kotlin.listener.SuperHttpListener
import com.hz.zxk.superhttp_kotlin.manager.ObserverManager
import com.hz.zxk.superutil_kotlin.utils.NetWorkUtil
import io.reactivex.observers.DisposableObserver
import io.reactivex.plugins.RxJavaPlugins
import okhttp3.ResponseBody
import java.lang.reflect.ParameterizedType

abstract class BaseDisposableObserver(
    var tag: String? = null
) : DisposableObserver<ResponseBody>() {

    init {
        RxJavaPlugins.setErrorHandler {
            Log.e("TAG", it.message ?: "RxJavaError")
        }
    }

    override fun onStart() {
        super.onStart()
        ObserverManager.instance.add(tag, this)
        onLoading()
    }

    override fun onComplete() {
        ObserverManager.instance.remove(tag)
    }

    override fun onNext(responseBody: ResponseBody) {
        Log.d("TAG", "onNext")
        onSuccess(responseBody)
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
        ObserverManager.instance.remove(tag)
        onFail(e)
    }

    abstract fun onLoading()

    abstract fun onSuccess(responseBody: ResponseBody)

    abstract fun onFail(e: Throwable)

}