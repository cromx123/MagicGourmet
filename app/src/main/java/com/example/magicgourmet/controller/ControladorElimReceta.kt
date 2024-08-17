package com.example.magicgourmet.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.magicgourmet.R
import com.example.magicgourmet.model.DatabaseHelper

class ControladorElimReceta : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_elim_receta)

        val btnVolver = findViewById<Button>(R.id.btnCancelelimReceta)
        val btnVolver2 = findViewById<Button>(R.id.btnCancelconelimReceta)
        val btnconfir = findViewById<Button>(R.id.btnAcceptelimReceta)
        val btnconfir2 = findViewById<Button>(R.id.btnAcceptconelimReceta)
        val mensajeconfirmarelim = findViewById<FrameLayout>(R.id.mensaje_confirmarelim)
        val nombreEditText = findViewById<EditText>(R.id.nom_recetaelim)
        val dbHelper = DatabaseHelper(this)

        btnVolver.setOnClickListener {
            finish() // Cierra la actividad actual y regresa a la anterior
        }

        btnVolver2.setOnClickListener {
            // Añade la acción deseada o elimina este botón si no es necesario
            mensajeconfirmarelim.visibility = View.GONE
        }

        btnconfir.setOnClickListener {
            mensajeconfirmarelim.visibility = View.VISIBLE
        }

        btnconfir2.setOnClickListener {
            val nombre = nombreEditText.text.toString()
            if (nombre.isNotEmpty()) {
                val filasAfectadas = dbHelper.eliminarReceta(nombre)
                if (filasAfectadas > 0) {
                    Toast.makeText(this, "Receta eliminada", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, ControladorPrincipal::class.java)
                    startActivity(intent) // Inicia la actividad principal después de la eliminación
                } else {
                    Toast.makeText(this, "Error al eliminar la receta o receta no encontrada", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, ingresa un nombre de receta", Toast.LENGTH_SHORT).show()
            }
        }
    }
}