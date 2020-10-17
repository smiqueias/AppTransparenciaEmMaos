package com.imagine.transparnciaemmosbrasilapp.general.services

import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("chave-api-dados", "45a79ac57f62c04b389ea8d9f06f7107")
            .build()
        return chain.proceed(request)
    }
}