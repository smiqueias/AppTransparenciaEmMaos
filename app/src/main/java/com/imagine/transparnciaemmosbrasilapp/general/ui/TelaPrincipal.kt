package com.imagine.transparnciaemmosbrasilapp.general.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.imagine.transparnciaemmosbrasilapp.R
import kotlinx.android.synthetic.main.tela_principal.*

class TelaPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_principal)

        view_gastosBolsaFamilia.setOnClickListener {
            startActivity(Intent(this@TelaPrincipal,TelaDeBuscaGastosBF::class.java))
        }

        view_emendasParlamentares.setOnClickListener {
            startActivity(Intent(this@TelaPrincipal,TelaFiltragemEmendas::class.java))

        }

    }
}