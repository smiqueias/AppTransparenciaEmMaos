package com.imagine.transparnciaemmosbrasilapp.general.model


import com.google.gson.annotations.SerializedName

class EmendasModel : ArrayList<EmendasModel.EmendasModelItem>(){
    data class EmendasModelItem(
        @SerializedName("ano")
        val ano: Int,
        @SerializedName("autor")
        val autor: String,
        @SerializedName("codigoEmenda")
        val codigoEmenda: String,
        @SerializedName("funcao")
        val funcao: String,
        @SerializedName("localidadeDoGasto")
        val localidadeDoGasto: String,
        @SerializedName("nomeAutor")
        val nomeAutor: String,
        @SerializedName("numeroEmenda")
        val numeroEmenda: String,
        @SerializedName("subfuncao")
        val subfuncao: String,
        @SerializedName("valorEmpenhado")
        val valorEmpenhado: String,
        @SerializedName("valorLiquidado")
        val valorLiquidado: String,
        @SerializedName("valorPago")
        val valorPago: String,
        @SerializedName("valorRestoAPagar")
        val valorRestoAPagar: String,
        @SerializedName("valorRestoCancelado")
        val valorRestoCancelado: String,
        @SerializedName("valorRestoInscrito")
        val valorRestoInscrito: String,
        @SerializedName("valorRestoPago")
        val valorRestoPago: String
    )
}