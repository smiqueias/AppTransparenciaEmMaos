package com.imagine.transparnciaemmosbrasilapp.general.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.imagine.transparnciaemmosbrasilapp.R
import com.imagine.transparnciaemmosbrasilapp.general.services.BolsaFamiliaService
import com.imagine.transparnciaemmosbrasilapp.general.services.CEPService
import com.imagine.transparnciaemmosbrasilapp.general.services.ServiceBuilderBolsaFamilia
import com.imagine.transparnciaemmosbrasilapp.general.services.ServiceBuilderCEP
import kotlinx.android.synthetic.main.activity_tela_de_busca_gastos_b_f.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TelaDeBuscaGastosBF : AppCompatActivity() {

    var codIbge: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_de_busca_gastos_b_f)

        bt_consultar.setOnClickListener {

            GlobalScope.launch(Dispatchers.IO) {

                if (et_municipio.text.isNotEmpty() && et_date.text.isNotEmpty()) {

                    if (et_date.text.length != 6) {
                        runOnUiThread() {
                            Toast.makeText(
                                baseContext, "Formato de Data Inválido",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    } else {
                        try {
                            getCodeIBGE()
                            getDataBolsaFamilia(codIbge)
                        } catch (e: Exception) {
                            runOnUiThread() {
                                Toast.makeText(
                                    baseContext, "Erro de conexão com o servidor",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }

                    }
                } else {
                    runOnUiThread() {
                        Toast.makeText(
                            baseContext,
                            "Preencha todos os campos!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            }
        }
    }


    suspend fun getCodeIBGE() {

        val destinationServiceCEP = ServiceBuilderCEP.buildServiceCEP(CEPService::class.java)

        val requestCall = destinationServiceCEP.fetchCEP(et_municipio.text.toString())

        if (requestCall.isSuccessful) {
            codIbge = requestCall.body()!!.ibge
        } else {
            runOnUiThread() {
                Toast.makeText(baseContext, "CEP INVÁLIDO", Toast.LENGTH_LONG).show()
            }

        }
    }

    suspend fun getDataBolsaFamilia(codIbge: String) {

        val destinationServiceBolsaFamilia = ServiceBuilderBolsaFamilia
            .buildServiceBolsaFamilia(BolsaFamiliaService::class.java)

        val requestCall = destinationServiceBolsaFamilia
            .fetchBolsaFamiliaDados(et_date.text.toString(), codIbge)

        if (requestCall.isSuccessful) {

            requestCall.body()!!.forEach {

                runOnUiThread() {
                    tv_valor.text = it.valor.toString()

                    tv_total_beneficiados.text = it.quantidadeBeneficiados.toString()
                }
            }

        }
    }

}






