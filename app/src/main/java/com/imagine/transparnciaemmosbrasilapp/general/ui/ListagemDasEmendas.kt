package com.imagine.transparnciaemmosbrasilapp.general.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.imagine.transparnciaemmosbrasilapp.R
import com.imagine.transparnciaemmosbrasilapp.general.adapter.EmendasAdapter
import com.imagine.transparnciaemmosbrasilapp.general.model.EmendasModel
import com.imagine.transparnciaemmosbrasilapp.general.services.apiservices.EmendasService
import com.imagine.transparnciaemmosbrasilapp.general.services.builderservices.ServiceBuilderEmendas
import kotlinx.android.synthetic.main.activity_listagem_das_emendas.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListagemDasEmendas : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listagem_das_emendas)

        val ano = intent.getStringExtra("ano")
        val numeroEmenda = intent.getStringExtra("numeroEmenda")
        val nomeAutor = intent.getStringExtra("nomeAutor")

        if (nomeAutor!!.isEmpty() && numeroEmenda!!.isEmpty()) {

            GlobalScope.launch(Dispatchers.IO) {

                try {
                    val destinationService = ServiceBuilderEmendas
                        .buildServiceEmendas(EmendasService::class.java)
                    val requestCall =
                        destinationService.fetchEmendasWithYear(ano.toString())

                    if (requestCall.isSuccessful && requestCall.body()?.size != 0) {

                        runOnUiThread() {
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
                        }
                        Log.i("REQUEST CODE", requestCall.code().toString())
                    }
                } catch (e: Exception) {
                    Toast.makeText(
                        baseContext,
                        "Erro de conexão",
                        Toast.LENGTH_LONG
                    ).show()
                    e.printStackTrace()
                }
            }
        }

        else if (nomeAutor.isNotEmpty() && numeroEmenda!!.isEmpty()) {

            GlobalScope.launch(Dispatchers.IO) {

                try {
                    val destinationServive = ServiceBuilderEmendas
                        .buildServiceEmendas(EmendasService::class.java)

                    val requestCall = destinationServive
                        .fetchEmendasWithYearAndAuthor(nomeAutor, ano.toString())

                    if (requestCall.isSuccessful && requestCall.body()?.size != 0) {

                        runOnUiThread() {
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
                        }
                        Log.i("REQUEST CODE", requestCall.code().toString())
                    }
                } catch (e: Exception) {
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
                        }
                        Log.i("REQUEST CODE", requestCall.code().toString())
                    }
                } catch (e: Exception) {
                    Toast.makeText(
                        baseContext,
                        "Erro de conexão",
                        Toast.LENGTH_LONG
                    ).show()
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