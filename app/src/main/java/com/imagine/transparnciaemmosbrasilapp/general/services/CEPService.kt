package com.imagine.transparnciaemmosbrasilapp.general.services


import com.imagine.transparnciaemmosbrasilapp.general.model.CEPModel
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Path

interface CEPService {

@GET("ws/{cep}/json/")
suspend fun fetchCEP(@Path("cep") cep: String): Response <CEPModel>
}