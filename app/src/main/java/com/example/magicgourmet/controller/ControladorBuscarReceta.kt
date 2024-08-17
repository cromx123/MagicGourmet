package com.example.magicgourmet.controller

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.magicgourmet.R
import com.example.magicgourmet.model.DatabaseHelper


class ControladorBuscarReceta: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_buscar_receta)
        val btnbuscarbr =findViewById<ImageButton>(R.id.btnbuscarbr)
        val btnselecingredientes =findViewById<Button>(R.id.btn_selecingredientesb)
        val btnbuscarreceta =findViewById<Button>(R.id.btn_buscarreceta)
        val nombrereceta = findViewById<EditText>(R.id.nombrerecetab)
        val dbHelper = DatabaseHelper(this)
        btnbuscarbr.setImageResource(R.drawable.busqueda_selec)

        btnbuscarreceta.setOnClickListener {
            val nombre = nombrereceta.text.toString()

            if (nombre.isNotEmpty()) {

                val buscarReceta = dbHelper.buscarReceta(nombre)

                Log.d("BuscarReceta", "Buscando receta con nombre: $buscarReceta")

                if (buscarReceta != null) {
                    val optionsIntent = Intent(this, ControladorRecetaVisualizada::class.java).apply {
                        putExtra("NombreRecetab", buscarReceta)
                    }
                    startActivity(optionsIntent)
                    Toast.makeText(this, "Receta encontrada", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error al buscar la receta", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Ingrese un nombre", Toast.LENGTH_SHORT).show()
            }
        }
        btnselecingredientes.setOnClickListener{
            startActivity(Intent(this, ControladorSelecFiltros::class.java ))
        }
        menuInferiorButtons()
    }

    private fun menuInferiorButtons() {

        val buttonActivityMap = mapOf(
            R.id.btnhomebr to ControladorPrincipal::class.java,
            R.id.btnbuscarbr to ControladorBuscarReceta::class.java,
            R.id.btncrearbr to ControladorAdminReceta::class.java,
            R.id.btnperfilbr to ControladorPerfilUsuario::class.java
        )

        buttonActivityMap.forEach { (buttonId, activityClass) ->
            findViewById<ImageButton>(buttonId).setOnClickListener {
                if (buttonId == R.id.btncrearbr) {
                    val optionsIntent = Intent(this, activityClass).apply {
                        putExtra("OpcionAdmReceta", "crear")
                    }
                    startActivity(optionsIntent)
                } else {
                    startActivity(Intent(this, activityClass))
                }
            }
        }
    }
}