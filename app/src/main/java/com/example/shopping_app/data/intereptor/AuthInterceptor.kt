package com.example.shopping_app.data.intereptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

//network isteklerinin arasına girip her adımda kendini api key verecek
//
class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request()

        val newUrl = requestBuilder.url.newBuilder()
            .build()

        val newRequest = requestBuilder.newBuilder().url(newUrl).build()

        return chain.proceed(newRequest)
    }
}