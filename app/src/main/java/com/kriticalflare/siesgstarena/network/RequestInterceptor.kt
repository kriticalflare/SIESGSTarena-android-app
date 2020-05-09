package com.kriticalflare.siesgstarena.network

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor: Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder()
        request.addHeader("Accept","application/vnd.arena+json;version=1")

        return chain.proceed(request.build())
    }

}