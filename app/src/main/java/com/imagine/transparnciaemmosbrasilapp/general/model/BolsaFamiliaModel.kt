package com.imagine.transparnciaemmosbrasilapp.general.model


import com.google.gson.annotations.SerializedName

class BolsaFamiliaModel : ArrayList<BolsaFamiliaModel.BolsaFamiliaModelItem>(){
    data class BolsaFamiliaModelItem(
        @SerializedName("dataReferencia")
        val dataReferencia: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("municipio")
        val municipio: Municipio,
        @SerializedName("quantidadeBeneficiados")
        val quantidadeBeneficiados: Int,
        @SerializedName("tipo")
        val tipo: Tipo,
        @SerializedName("valor")
        val valor: Int
    ) {
        data class Municipio(
            @SerializedName("codigoIBGE")
            val codigoIBGE: String,
            @SerializedName("nomeIBGE")
            val nomeIBGE: String,
            @SerializedName("nomeIBGEsemAcento")
            val nomeIBGEsemAcento: String,
            @SerializedName("pais")
            val pais: String,
            @SerializedName("uf")
            val uf: Uf
        ) {
            data class Uf(
                @SerializedName("nome")
                val nome: String,
                @SerializedName("sigla")
                val sigla: String
            )
        }
    
        data class Tipo(
            @SerializedName("descricao")
            val descricao: String,
            @SerializedName("descricaoDetalhada")
            val descricaoDetalhada: String,
            @SerializedName("id")
            val id: Int
        )
    }
}