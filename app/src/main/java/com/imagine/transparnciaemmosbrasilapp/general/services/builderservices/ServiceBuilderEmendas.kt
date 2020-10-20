package com.imagine.transparnciaemmosbrasilapp.general.services.builderservices

import com.imagine.transparnciaemmosbrasilapp.general.utils.MyInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilderEmendas {

    private val okHttp = OkHttpClient.Builder().apply {
        addInterceptor(MyInterceptor())
    }.build()

    private const val URL = "http://www.portaltransparencia.gov.br/"

    private val builder = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp)

    private val retrofit = builder.build()

    fun <T> buildServiceEmendas(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }

}