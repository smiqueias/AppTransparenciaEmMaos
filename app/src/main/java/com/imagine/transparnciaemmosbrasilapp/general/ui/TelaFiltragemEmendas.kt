package com.imagine.transparnciaemmosbrasilapp.general.ui

import android.app.Service
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.imagine.transparnciaemmosbrasilapp.R
import com.imagine.transparnciaemmosbrasilapp.general.services.apiservices.EmendasService
import com.imagine.transparnciaemmosbrasilapp.general.services.builderservices.ServiceBuilderEmendas
import kotlinx.android.synthetic.main.activity_tela_filtragem_emendas.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TelaFiltragemEmendas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_filtragem_emendas)

        bt_consultar.setOnClickListener {

            GlobalScope.launch(Dispatchers.IO) {

                getEmendasWithYear()
            }

        }

    }

    suspend fun getEmendasWithYear() {

        val destinationService = ServiceBuilderEmendas
                                .buildServiceEmendas(EmendasService::class.java)
        val requesCall = destinationService.fetchEmendasWithYear(et_anoEmenta.text.toString().toInt())

        if(requesCall.isSuccessful) {

            requesCall.body()!!.forEach {
                runOnUiThread() { tv_teste.text = it.ano.toString() }
            }
        }
        else { runOnUiThread() {tv_teste.text = requesCall.code().toString()}}

    }
}