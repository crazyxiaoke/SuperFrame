package com.hz.zxk.superhttp_kotlin

import android.content.Context
import com.hz.zxk.superhttp_kotlin.listener.SuperCallback
import com.hz.zxk.superhttp_kotlin.request.ISuperRequest
import com.hz.zxk.superhttp_kotlin.request.RetrofitRequest
import java.lang.IllegalArgumentException
import java.lang.NullPointerException

class SuperHttp private constructor() {
    private var request: ISuperRequest? = null
    private var method: Method = Method.GET
    private var url: String? = null
    private var params: HashMap<String, Any>? = null
    private var body: Any? = null

    init {
        params = HashMap()
    }

    companion object {
        val instance: SuperHttp by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            SuperHttp()
        }
    }

    fun init(context: Context, baseUrl: String, request: ISuperRequest? = null) {
        if (request == null) {
            this.request = RetrofitRequest.instance
        } else {
            this.request = request
        }
        this.request?.init(context, baseUrl)
    }

    fun openDebug(isDebug: Boolean = true) {
        this.request?.openDebug(isDebug)
    }

    fun method(method: Method): SuperHttp {
        this.method = method
        return this
    }

    fun url(url: String): SuperHttp {
        this.url = url
        return this
    }

    fun addParam(key: String, value: Any): SuperHttp {
        params!![key] = value
        return this
    }

    fun addParams(params: HashMap<String, Any>): SuperHttp {
        this.params!!.putAll(params)
        return this
    }

    fun <T> addBody(params: T): SuperHttp {
        this.body = params
        return this
    }


    fun <T> request(tag: String?, listener: SuperCallback<T>?) {
        if (url == null) {
            throw NullPointerException("url is null")
        }
        when (method) {
            Method.GET -> request!!.get(url!!, params, tag, listener)
            Method.POST ->
                when (body != null) {
                    true -> request!!.post(url!!, body)
                    false -> request!!.post(url!!, params)
                }
            Method.PUT -> request!!.put(url!!, params)
            Method.DELETE -> request!!.delete(url!!, params)
            else ->
                throw IllegalArgumentException("不支持的${method}请求方式")
        }
    }

    enum class Method {
        GET, POST, PUT, DELETE
    }

}