package com.hz.zxk.superhttp_kotlin.listener

interface SuperHttpListener<T> {
    fun onStart()

    fun onSuccess(data: T)

    fun onError(e: Throwable)

}