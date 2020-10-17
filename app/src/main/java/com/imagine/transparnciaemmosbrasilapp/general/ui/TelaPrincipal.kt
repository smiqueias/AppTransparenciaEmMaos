package com.imagine.transparnciaemmosbrasilapp.general.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.imagine.transparnciaemmosbrasilapp.R
import kotlinx.android.synthetic.main.activity_main.*

class TelaPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        view_gastosBolsaFamilia.setOnClickListener {
            startActivity(Intent(this@TelaPrincipal,TelaDeBuscaGastosBF::class.java))
        }

    }
}