package com.imagine.transparnciaemmosbrasilapp.general.services

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilderBolsaFamilia {




    private val okHttp = OkHttpClient.Builder().apply {
        addInterceptor(MyInterceptor())
    }.build()

    private const val URL = "http://www.portaltransparencia.gov.br/"

    private val builder = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp)


    private val retrofit = builder.build()

    fun <T> buildServiceBolsaFamilia(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}