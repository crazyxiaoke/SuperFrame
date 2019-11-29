package com.hz.zxk.superhttp_kotlin.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*


interface ApiService {
    @GET("{url}")
    fun get(
        @Path(value = "url", encoded = true) url: String,
        @QueryMap params: @JvmSuppressWildcards Map<String, Any>?
    ): Observable<String>

    @POST("{url}")
    @FormUrlEncoded
    fun post(
        @Path(value = "url", encoded = true) url: String,
        @FieldMap params: Map<String, Any>?
    ): Observable<ResponseBody>

    @POST("{url}")
    fun <T> post(
        @Path(value = "url", encoded = true) url: String,
        @Body params: T?
    ): Observable<ResponseBody>

    @DELETE("{url}")
    fun delete(
        @Path(value = "url", encoded = true) url: String,
        @QueryMap params: Map<String, Any>?
    ): Observable<ResponseBody>

}