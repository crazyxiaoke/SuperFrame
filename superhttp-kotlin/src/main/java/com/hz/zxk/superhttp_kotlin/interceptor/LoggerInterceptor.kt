package com.hz.zxk.superhttp_kotlin.interceptor

import android.util.Log
import com.google.gson.JsonObject
import com.orhanobut.logger.Logger
import okhttp3.*
import okio.Buffer
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class LoggerInterceptor(var isDebug: Boolean) : Interceptor {
    private val TAG = "SuperHttp"
    override fun intercept(chain: Interceptor.Chain): Response {
        printLog("================Request Start================")
        val request = chain.request()
        val headers = request.headers()
        val connection = chain.connection()
        printLog("===>${request.method()}  ${request.url()} ${if (connection != null) " " + connection.protocol() else ""}")
        if (headers.size() > 0) {
            printLog("==================Headers====================")
            for (i in 0 until headers.size()) {
                logHeader(headers, i)
            }
            printLog("=============================================")
            printLog("|||||||||||||||||||||||||||||||||||||||||||||")
        }
        printLog("==================请求参数====================")
        printLog("${bodyToString(request.body())}")
        printLog("=============================================")
        val startTime = System.currentTimeMillis()
        val response = chain.proceed(request)
        printLog("===>${response.code()}  ${request.url()}")
        val endTime = System.currentTimeMillis()
        val duration = endTime - startTime
        var responseBody = response.body()
        val body = responseBody?.string()
        responseBody = ResponseBody.create(responseBody?.contentType(), body)
        printJson(body)
        printLog("================Request End:${duration}ms================")
        return response.newBuilder().body(responseBody).build()
    }

    /**
     * 将请求提转为String
     *
     * @param request
     * @return
     */
    private fun bodyToString(request: RequestBody?): String? {
        return try {
            request?.let {
                val buffer = Buffer()
                it.writeTo(buffer)
                buffer.readUtf8()
            }
        } catch (e: IOException) {
            ""
        }
    }

    private fun printLog(log: String?) {
        if (isDebug) {
            if (log != null) {
                Log.d(TAG, log)
            } else {
                Log.d(TAG, "")
            }
        }
    }

    private fun printJson(log: String?) {
        log?.let { it ->
            val json = it.trim()
            if (json.startsWith("{")) {
                val jsonObject = JSONObject(json)
                var message = jsonObject.toString(2)
                printLog(message)
                return
            }
            if (json.startsWith("[")) {
                val jsonArray = JSONArray(json)
                val message = jsonArray.toString(2)
                printLog(message)
                return
            }
        }
    }

    private fun logHeader(headers: Headers, i: Int) {
        val value = headers.value(i)
        printLog(headers.name(i) + ": " + value)
    }


}