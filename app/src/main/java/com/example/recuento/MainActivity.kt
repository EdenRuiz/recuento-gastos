package com.example.recuento

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val listaGastos = mutableListOf<Gasto>()
    private lateinit var adapter: GastoAdapter
    private var totalGastos = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etConcepto = findViewById<EditText>(R.id.etConcepto)
        val etMonto = findViewById<EditText>(R.id.etMonto)
        val btnAgregar = findViewById<Button>(R.id.btnAgregar)
        val tvTotal = findViewById<TextView>(R.id.tvTotal)
        val recyclerGastos = findViewById<RecyclerView>(R.id.recyclerGastos)

        adapter = GastoAdapter(listaGastos)
        recyclerGastos.layoutManager = LinearLayoutManager(this)
        recyclerGastos.adapter = adapter

        btnAgregar.setOnClickListener {
            val concepto = etConcepto.text.toString()
            val montoStr = etMonto.text.toString()

            if (concepto.isNotEmpty() && montoStr.isNotEmpty()) {
                val monto = montoStr.toDouble()
                val nuevoGasto = Gasto(concepto, monto)
                
                listaGastos.add(nuevoGasto)
                adapter.notifyItemInserted(listaGastos.size - 1)
                
                totalGastos += monto
                tvTotal.text = getString(R.string.total_label, totalGastos)

                etConcepto.text.clear()
                etMonto.text.clear()
            } else {
                Toast.makeText(this, getString(R.string.error_campos_vacios), Toast.LENGTH_SHORT).show()
            }
        }
    }
}