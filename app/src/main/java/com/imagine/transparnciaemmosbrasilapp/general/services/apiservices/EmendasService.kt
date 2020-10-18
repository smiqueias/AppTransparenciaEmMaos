package com.imagine.transparnciaemmosbrasilapp.general.services.apiservices

import com.imagine.transparnciaemmosbrasilapp.general.model.EmendasModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EmendasService {

    @GET("api-de-dados/emendas")
    suspend fun fetchEmendasWithYear(@Query("ano") ano: Int): Response<EmendasModel>

    @GET("api-de-dados/emendas")
    suspend fun fetchEmendasWithYearAndAuthor(
        @Query("nomeAutor") nomeAutor: String,
        @Query("ano") ano: String
    ): Response<EmendasModel>

    @GET("api-de-dados/emendas")
    suspend fun fetchEmendasWithYearAndAuthorAndNumber(
        @Query("nomeAutor") nomeAutor: String,
        @Query("ano") ano: String,
        @Query("numeroEmenda") numeroEmenda: String
    ): Response<EmendasModel>
}