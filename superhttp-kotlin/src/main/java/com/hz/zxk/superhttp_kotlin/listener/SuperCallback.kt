package com.hz.zxk.superhttp_kotlin.listener

import android.util.Log
import java.lang.RuntimeException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

abstract class SuperCallback<T> {
    var mType: Type

    init {
        mType = getSuperclassTypeParameter(javaClass)
    }

    protected fun getSuperclassTypeParameter(subClass: Class<*>): Type {
        val superclass: Type? = subClass.genericSuperclass
        if (superclass != null && superclass is ParameterizedType) {
            return superclass.actualTypeArguments[0]
        }
        throw RuntimeException("Miss type parameter")
    }

    abstract fun onStart()

    abstract fun onSuccess(data: T?)

    abstract fun onError(e: Throwable)

}