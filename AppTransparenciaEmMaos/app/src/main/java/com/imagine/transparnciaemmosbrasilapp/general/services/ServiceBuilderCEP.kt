package com.imagine.transparnciaemmosbrasilapp.general.services

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ServiceBuilderCEP {

    private const val URL = "https://viacep.com.br/"



    private val builder = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())


    private val retrofit = builder.build()

    fun <T> buildServiceCEP(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}