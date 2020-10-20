package com.imagine.transparnciaemmosbrasilapp.general.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.imagine.transparnciaemmosbrasilapp.R
import kotlinx.android.synthetic.main.activity_tela_filtragem_emendas.*

class TelaFiltragemEmendas : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_filtragem_emendas)

        bt_consultar.setOnClickListener {

            if (et_anoEmenda.text.isNotEmpty()) {

                val i = Intent(this@TelaFiltragemEmendas, ListagemDasEmendas::class.java)

                i.putExtra("ano", et_anoEmenda.text.toString())
                i.putExtra("nomeAutor", et_autor_emenda.text.toString())
                i.putExtra("numeroEmenda", et_numero_emenda.text.toString())

                startActivity(i)

            } else {
                Toast.makeText(
                    baseContext, "Informe o ano da emenda para a busca", Toast.LENGTH_LONG
                ).show()
            }
       }

    }
}