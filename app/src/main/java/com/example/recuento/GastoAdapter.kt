package com.example.recuento

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GastoAdapter(private val gastos: List<Gasto>) : RecyclerView.Adapter<GastoAdapter.GastoViewHolder>() {

    class GastoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvConcepto: TextView = view.findViewById(R.id.tvConceptoItem)
        val tvMonto: TextView = view.findViewById(R.id.tvMontoItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GastoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_gasto, parent, false)
        return GastoViewHolder(view)
    }

    override fun onBindViewHolder(holder: GastoViewHolder, position: Int) {
        val gasto = gastos[position]
        holder.tvConcepto.text = gasto.concepto
        holder.tvMonto.text = holder.itemView.context.getString(R.string.monto_formato, gasto.monto)
    }

    override fun getItemCount(): Int = gastos.size
}