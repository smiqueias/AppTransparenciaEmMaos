package com.imagine.transparnciaemmosbrasilapp.general.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.imagine.transparnciaemmosbrasilapp.R
import com.imagine.transparnciaemmosbrasilapp.general.model.EmendasModel
import kotlinx.android.synthetic.main.layout_lista_ementas.view.*

class EmendasAdapter(private val context: Context,private val emendas: MutableList<EmendasModel>) :
    RecyclerView.Adapter<EmendasAdapter.EmendaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmendaViewHolder {

        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.layout_lista_ementas,parent,false)

        return EmendaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EmendaViewHolder, position: Int) {
        val emenda = emendas[position]
        holder.bind(emenda)
    }

    override fun getItemCount(): Int {
        return emendas.size
    }

    class EmendaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(emendasModel: EmendasModel) {
            itemView.tv_ano.text = emendasModel.ano
            itemView.tv_valor_liquidado.text = emendasModel.valorLiquidado
            itemView.tv_valor_empenhado.text = emendasModel.valorEmpenhado
            itemView.tv_valor_pago.text = emendasModel.valorPago
            itemView.tv_localidade_gasto.text = emendasModel.localidadeDoGasto
            itemView.tv_funcao.text = emendasModel.funcao
            itemView.tv_subFuncao.text = emendasModel.subfuncao

        }
    }
}