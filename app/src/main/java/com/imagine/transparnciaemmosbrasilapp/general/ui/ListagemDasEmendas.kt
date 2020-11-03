package com.imagine.transparnciaemmosbrasilapp.general.ui


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.imagine.transparnciaemmosbrasilapp.R
import com.imagine.transparnciaemmosbrasilapp.general.adapter.EmendasAdapter
import com.imagine.transparnciaemmosbrasilapp.general.model.EmendasModel
import com.imagine.transparnciaemmosbrasilapp.general.services.apiservices.EmendasService
import com.imagine.transparnciaemmosbrasilapp.general.services.builderservices.ServiceBuilderEmendas
import kotlinx.android.synthetic.main.activity_listagem_das_emendas.*
import kotlinx.android.synthetic.main.activity_tela_de_busca_gastos_b_f.*
import kotlinx.android.synthetic.main.activity_tela_filtragem_emendas.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListagemDasEmendas : AppCompatActivity() {


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listagem_das_emendas)

        val ano = intent.getStringExtra("ano")
        val numeroEmenda = intent.getStringExtra("numeroEmenda")
        val nomeAutor = intent.getStringExtra("nomeAutor")

        if (nomeAutor!!.isEmpty() && numeroEmenda!!.isEmpty()) {

            pb_carregar2.visibility = View.VISIBLE

            GlobalScope.launch(Dispatchers.IO) {

                try {
                    val destinationService = ServiceBuilderEmendas
                        .buildServiceEmendas(EmendasService::class.java)
                    val requestCall =
                        destinationService.fetchEmendasWithYear(ano.toString())

                    if (requestCall.isSuccessful && requestCall.body()?.size != 0) {

                        runOnUiThread() {
                            pb_carregar2.visibility = View.GONE
                            startRecyclerView(requestCall.body() as MutableList<EmendasModel>)

                        }
                    }

                    else {
                        runOnUiThread() {
                            Toast.makeText(
                                baseContext,
                                "Nenhum resultado encontrado na busca",
                                Toast.LENGTH_LONG
                            ).show()
                            tv_nenhumResultado.text = "NENHUM RESULTADO ENCONTRADO NA BUSCA"
                            pb_carregar2.visibility = View.GONE
                        }
                        Log.i("REQUEST CODE", requestCall.code().toString())
                    }
                } catch (e: Exception) {
                    runOnUiThread() {

                        pb_carregar.visibility = View.GONE

                        Toast.makeText(
                            baseContext,
                            "Erro de conexão",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    e.printStackTrace()
                }
            }
        }

        else if (nomeAutor.isNotEmpty() && numeroEmenda!!.isEmpty()) {

            pb_carregar2.visibility = View.VISIBLE

            GlobalScope.launch(Dispatchers.IO) {

                try {
                    val destinationServive = ServiceBuilderEmendas
                        .buildServiceEmendas(EmendasService::class.java)

                    val requestCall = destinationServive
                        .fetchEmendasWithYearAndAuthor(nomeAutor, ano.toString())

                    if (requestCall.isSuccessful && requestCall.body()?.size != 0) {

                        runOnUiThread() {
                            pb_carregar2.visibility = View.GONE
                            startRecyclerView(requestCall.body() as MutableList<EmendasModel>)
                        }

                    }

                    else {
                        runOnUiThread() {
                            Toast.makeText(
                                baseContext,
                                "Nenhum resultado encontrado na busca",
                                Toast.LENGTH_LONG
                            ).show()
                            tv_nenhumResultado.text = "NENHUM RESULTADO ENCONTRADO NA BUSCA"
                            pb_carregar2.visibility = View.GONE
                        }
                        Log.i("REQUEST CODE", requestCall.code().toString())
                    }
                } catch (e: Exception) {

                    pb_carregar.visibility = View.GONE

                    Toast.makeText(
                        baseContext,
                        "Erro de conexão",
                        Toast.LENGTH_LONG
                    ).show()
                    e.printStackTrace()
                }
            }


        }

        else {

            pb_carregar2.visibility = View.VISIBLE

            GlobalScope.launch(Dispatchers.IO) {

                try {
                    val destinationService = ServiceBuilderEmendas
                        .buildServiceEmendas(EmendasService::class.java)

                    val requestCall = destinationService
                        .fetchEmendasWithYearAndAuthorAndNumber(
                            nomeAutor.toString(),
                            ano.toString(),
                            numeroEmenda.toString()
                        )

                    if (requestCall.isSuccessful && requestCall.body()?.size != 0) {

                        runOnUiThread() {
                            pb_carregar2.visibility = View.GONE
                            startRecyclerView(requestCall.body() as MutableList<EmendasModel>)
                        }

                    }

                    else {
                        runOnUiThread() {

                            Toast.makeText(
                                baseContext,
                                "Nenhum resultado encontrado na busca",
                                Toast.LENGTH_LONG
                            ).show()
                            tv_nenhumResultado.text = "NENHUM RESULTADO ENCONTRADO NA BUSCA"
                            pb_carregar2.visibility = View.GONE
                        }
                        Log.i("REQUEST CODE", requestCall.code().toString())
                    }
                } catch (e: Exception) {

                    startActivity(Intent(this@ListagemDasEmendas, TelaFiltragemEmendas::class.java))

                    runOnUiThread() {

                        pb_carregar.visibility = View.GONE

                        Toast.makeText(
                            baseContext,
                            "Erro de conexão",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    e.printStackTrace()
                }
            }

        }
    }

    private fun startRecyclerView(mutableListRequest: MutableList<EmendasModel>) {
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = EmendasAdapter(baseContext, mutableListRequest)
    }
}