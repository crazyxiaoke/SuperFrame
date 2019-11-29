package com.hz.zxk.superhttp_kotlin.exeption

import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.HttpException
import java.io.NotSerializableException
import java.lang.ClassCastException
import java.lang.NullPointerException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException


class SuperException(val code: Int?, val throwable: Throwable) : Throwable() {
    override lateinit var message: String
    fun handleException(e: Throwable): SuperException {
        val exception: SuperException?
        when (e) {
            is HttpException -> {
                exception = SuperException(e.code(), e)
                exception.message = e.message()
            }
            is JsonParseException,
            is JSONException,
            is JsonSyntaxException,
            is NotSerializableException,
            is ParseException -> {
                exception = SuperException(Error.PARSE_ERROR, e)
                exception.message = "解析错误"
            }
            is ClassCastException -> {
                exception = SuperException(Error.CASE_ERROR, e)
                exception.message = "类型转换错误"
            }
            is ConnectException -> {
                exception = SuperException(Error.NETWORK_ERROR, e)
                exception.message = "连接失败"
            }
            is ConnectTimeoutException,
            is SocketTimeoutException -> {
                exception = SuperException(Error.TIMEOUT_ERROR, e)
                exception.message = "连接超时"
            }
            is UnknownHostException -> {
                exception = SuperException(Error.UNKNOWHOST_ERROR, e)
                exception.message = "无法解析域名"
            }
            is NullPointerException -> {
                exception = SuperException(Error.NULLPOINTER_ERROR, e)
                exception.message = "NullPointerException"
            }
            else -> {
                exception = SuperException(Error.UNKNOWN, e)
                exception.message = e.message.toString()
            }
        }
        return exception
    }
}

class Error() {
    companion object {
        const val UNKNOWN = 10001
        const val PARSE_ERROR = UNKNOWN + 1
        const val NETWORK_ERROR = PARSE_ERROR + 1
        const val HTTP_ERROR = NETWORK_ERROR + 1
        const val TIMEOUT_ERROR = HTTP_ERROR + 1
        const val INVOKE_ERROR = TIMEOUT_ERROR + 1
        const val CASE_ERROR = INVOKE_ERROR + 1
        const val UNKNOWHOST_ERROR = CASE_ERROR + 1
        const val NULLPOINTER_ERROR = UNKNOWHOST_ERROR + 1
    }


}