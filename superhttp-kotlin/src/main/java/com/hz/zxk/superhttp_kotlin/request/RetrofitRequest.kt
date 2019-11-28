package com.hz.zxk.superhttp_kotlin.request

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.hz.zxk.superhttp_kotlin.api.ApiService
import com.hz.zxk.superhttp_kotlin.base.BaseDisposableObserver
import com.hz.zxk.superhttp_kotlin.interceptor.LoggerInterceptor
import com.hz.zxk.superhttp_kotlin.listener.SuperHttpListener
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import org.reactivestreams.Subscriber
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.lang.NullPointerException
import java.lang.reflect.ParameterizedType
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
        tag: String?, listener: SuperHttpListener<T>?
    ) {
        param?.keys?.forEach {
            Log.d("TAG", "${it}=${param[it]}")
        }
        retrofit?.create(ApiService::class.java)?.get(url, param)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : BaseDisposableObserver(tag) {
                override fun onLoading() {
                    listener?.onStart()
                }

                override fun onSuccess(responseBody: ResponseBody) {
                    Log.d("TAG", "onSuccess")
                    val clazz = listener?.javaClass
                    val type = clazz?.genericInterfaces
                    Log.d("TAG", "type=${type}");
                    Log.d("TAG", "type is ParameterizedType=${type is ParameterizedType}")
                    if (type != null && type is ParameterizedType) {
                        val tClass = type.actualTypeArguments[0] as Class<T>
                        val t = Gson().fromJson(responseBody.toString(), tClass)
                        Log.d("TAG", "t=${t}")
                        listener.onSuccess(t)
                    }
                }

                override fun onFail(e: Throwable) {
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