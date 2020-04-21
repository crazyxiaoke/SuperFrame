package com.hz.zxk.superhttp_kotlin.base

import android.util.Log
import com.google.gson.Gson
import com.hz.zxk.superhttp_kotlin.manager.ObserverManager
import io.reactivex.exceptions.Exceptions
import io.reactivex.observers.DisposableObserver
import io.reactivex.plugins.RxJavaPlugins
import java.lang.Exception
import java.lang.reflect.Type


abstract class BaseDisposableObserver<T>(
    var type: Type?,
    var tag: String? = null
) : DisposableObserver<String>() {
    private val TAG = "SuperHttp"

    init {
        RxJavaPlugins.setErrorHandler {
            it.printStackTrace()
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

    override fun onNext(result: String) {
        try {
            if (type == null) {
                onSuccess(null)
            } else {
                Log.d("TAG", "$type")
                onSuccess(Gson().fromJson(result, type))
            }
        } catch (e: Exception) {
            Exceptions.throwIfFatal(e)
            onError(e)
        }
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
        ObserverManager.instance.remove(tag)
        onFail(e)

    }

    abstract fun onLoading()

    abstract fun onSuccess(t: T?)

    abstract fun onFail(e: Throwable)

}