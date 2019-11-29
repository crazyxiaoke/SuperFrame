package com.hz.zxk.superhttp_kotlin.request

import android.content.Context
import com.hz.zxk.superhttp_kotlin.api.ApiService
import com.hz.zxk.superhttp_kotlin.base.BaseDisposableObserver
import com.hz.zxk.superhttp_kotlin.converter.MyGsonFactory
import com.hz.zxk.superhttp_kotlin.interceptor.LoggerInterceptor
import com.hz.zxk.superhttp_kotlin.listener.SuperCallback
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.File
import java.lang.NullPointerException
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

class RetrofitRequest private constructor() : ISuperRequest {

    private var cacheSize = 10 * 1024 * 1024L

    private var okHttpClient: OkHttpClient? = null
    private var retrofit: Retrofit? = null
    private var baseUrl: String? = null
    private var context: Context? = null
    private var isDebug: Boolean = false

    companion object {
        val instance: RetrofitRequest by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitRequest()
        }
    }

    override fun init(context: Context, baseUrl: String) {
        this.context = context
        this.baseUrl = baseUrl
        initClient()
        initRetrofit()
    }

    override fun openDebug(isDebug: Boolean) {
        this.isDebug = isDebug
    }


    private fun initClient() {
        okHttpClient = OkHttpClient.Builder()
            .cache(getCache())
            .addInterceptor(LoggerInterceptor(isDebug))
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    private fun initRetrofit() {
        if (baseUrl == null) {
            throw NullPointerException("请指定baseUrl")
        }
        retrofit = Retrofit.Builder()
            .client(okHttpClient!!)
            .addConverterFactory(MyGsonFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl!!)
            .build()
    }

    private fun getCache(): Cache? {
        checkContextIsNull(context)
        val file = File(context!!.cacheDir, "responses")
        return Cache(file, cacheSize)
    }

    private fun checkContextIsNull(context: Context?) {
        if (context == null)
            throw NullPointerException("context is NULL")
    }

    override fun <T> get(
        url: String, param: Map<String, Any>?,
        tag: String?, listener: SuperCallback<T>?
    ) {
        var type: Type? = null
        if (listener != null) {
            type = listener.mType
        }
        retrofit?.create(ApiService::class.java)?.get(url, param)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : BaseDisposableObserver<T>(type, tag) {
                override fun onLoading() {
                    listener?.onStart()
                }

                override fun onSuccess(t: T?) {
                    listener?.onSuccess(t)
                }

                override fun onFail( e: Throwable) {
                    listener?.onError(e)
                }
            })
    }

    override fun post(url: String, param: Map<String, Any>?) {
    }

    override fun <T> post(url: String, param: T?) {
    }

    override fun delete(url: String, param: Map<String, Any>?) {
    }

    override fun put(url: String, param: Map<String, Any>?) {
    }

}