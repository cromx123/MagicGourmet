package com.example.magicgourmet.controller

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.magicgourmet.R
import com.example.magicgourmet.model.DatabaseHelper


class ControladorElimIngrediente: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_elim_ingrediente)

        val btnCancelarIng = findViewById<Button>(R.id.btn_cancelar_eliminaring)
        val btnEliminarIngrediente = findViewById<Button>(R.id.btn_eliminar_ing)
        val dbHelper = DatabaseHelper(this)

        val editTextNombre = findViewById<EditText>(R.id.nombreingredienteeliminar)
        btnCancelarIng.setOnClickListener {
            finish()
        }
        btnEliminarIngrediente.setOnClickListener {
            // Obtener el texto ingresado por el usuario
            val nom = editTextNombre.text.toString()

            if (nom.isNotEmpty()) {
                // Realiza peticion al modelo, y el modelo ejecuta la funcion correspondiente
                val ingId = dbHelper.eliminarIng(nom)
                if (ingId != -1) {
                    Toast.makeText(this, "Ingrediente Eliminado", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "El ingrediente no se puede eliminar, puesto que existen recetas que lo utilizan", Toast.LENGTH_SHORT).show()
                }

            }
            else {
                Toast.makeText(this, "Ingrese un ingrediente", Toast.LENGTH_SHORT).show()
            }
        }
    }
}