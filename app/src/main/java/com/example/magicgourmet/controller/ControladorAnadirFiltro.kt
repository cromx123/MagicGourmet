package com.example.magicgourmet.controller

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.magicgourmet.R
import com.example.magicgourmet.model.DatabaseHelper

class ControladorAnadirFiltro: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_anadir_filtro)

        val btnCancelarFiltro = findViewById<Button>(R.id.btn_cancelFiltro)
        val btnAnadirFiltro = findViewById<Button>(R.id.btn_acepFiltro)
        val dbHelper = DatabaseHelper(this)

        val editTextNombre = findViewById<EditText>(R.id.nombFiltro)
        val editTextDesc = findViewById<EditText>(R.id.desc_filtro)
        btnCancelarFiltro.setOnClickListener {
            finish()
        }
        btnAnadirFiltro.setOnClickListener {
            // Obtener el texto ingresado por el usuario
            val nom = editTextNombre.text.toString()

            if (nom.isNotEmpty()) {
                // Realiza peticion al modelo, y el modelo ejecuta la funcion correspondiente
                val ingId = dbHelper.anadirIng(nom)
                if (ingId != -1L) {
                    Toast.makeText(this, "Ingrediente añadido", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error al añadir ingrediente", Toast.LENGTH_SHORT).show()
                }
                finish()
            }
            else {
                Toast.makeText(this, "Ingrese un nombre", Toast.LENGTH_SHORT).show()
            }
        }
    }
}