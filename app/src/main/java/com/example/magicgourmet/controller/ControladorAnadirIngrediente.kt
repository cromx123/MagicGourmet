package com.example.magicgourmet.controller

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.magicgourmet.R
import com.example.magicgourmet.model.DatabaseHelper

class ControladorAnadirIngrediente: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_anadir_ingrediente)

        val btnCancelarIng = findViewById<Button>(R.id.CancelarIng)
        val btnAnadirIngrediente = findViewById<Button>(R.id.AnadirIngrediente)
        val dbHelper = DatabaseHelper(this)

        val editTextNombre = findViewById<EditText>(R.id.editTexNomIngrediente)
        btnCancelarIng.setOnClickListener {
            finish()
        }
        btnAnadirIngrediente.setOnClickListener {
            // Obtener el texto ingresado por el usuario
            val nom = editTextNombre.text.toString()

            if (nom.isNotEmpty()) {
                // Realiza peticion al modelo, y el modelo ejecuta la funcion correspondiente
                val ingId = dbHelper.anadirIng(nom)
                if (ingId != -1L) {
                    Toast.makeText(this, "Ingrediente añadido", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "El ingrediente ya existe", Toast.LENGTH_SHORT).show()
                }

            }
             else {
                Toast.makeText(this, "Ingrese un ingrediente", Toast.LENGTH_SHORT).show()
             }
        }
    }
}