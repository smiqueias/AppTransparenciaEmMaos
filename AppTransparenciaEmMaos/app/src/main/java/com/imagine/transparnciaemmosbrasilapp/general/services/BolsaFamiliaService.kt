package com.imagine.transparnciaemmosbrasilapp.general.services


import com.imagine.transparnciaemmosbrasilapp.general.model.BolsaFamiliaModel
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Query


interface BolsaFamiliaService {


    @GET("api-de-dados/bolsa-familia-por-municipio")
     suspend fun fetchBolsaFamiliaDados(
        @Query("mesAno") data: String,
        @Query("codigoIbge") codIBGE: String
    ): Response <BolsaFamiliaModel>
}