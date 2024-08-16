package com.example.magicgourmet.controller

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.magicgourmet.R
import com.example.magicgourmet.model.DatabaseHelper
import com.example.magicgourmet.model.Ingrediente
import com.example.magicgourmet.model.Paso
import com.example.magicgourmet.model.Receta


class ControladorAnadirReceta: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_ingresar_receta)
        val btnVolver = findViewById<Button>(R.id.btnCancelReceta)
        val btnConfirmar = findViewById<Button>(R.id.btnAcceptReceta)
        // Extraer Nombre, Descripcion, Paso a paso, LInk
        val editTextNombre = findViewById<EditText>(R.id.nom_receta)
        val editTextDescrip = findViewById<EditText>(R.id.desc_receta)
        val editTextPaso = findViewById<EditText>(R.id.paso_receta)
        val editTextLink = findViewById<EditText>(R.id.link_video)
        val dbHelper = DatabaseHelper(this)
        // Cancelar la creacion de receta
        btnVolver.setOnClickListener {
            finish()
        }
        // Insertar la receta en la base de datos
        btnConfirmar.setOnClickListener {
            // Obtener el texto ingresado por el usuario
            val nombre = editTextNombre.text.toString()
            val descripcion = editTextDescrip.text.toString()
            val paso = editTextPaso.text.toString()
            val link = editTextLink.text.toString()
            if (nombre.isNotEmpty()){
                // Se juntan los datos para poder
                val nuevaReceta = Receta(
                    nombre = nombre,
                    descripcion = descripcion,
                    ingredientes = "Manzanas, Harina, Azúcar", // Puede ser un string separado por comas o puedes usar la lista de ingredientes
                    link = link,
                    imagen = "ruta/a/la/imagen"
                )
                val ingredientes = listOf(
                    Ingrediente(id = 1, nombre = "Manzanas"),
                    Ingrediente(id = 2, nombre = "Harina"),
                    Ingrediente(id = 3, nombre = "Azúcar")
                )

                val pasos = Paso(
                    descripcion = paso
                )
                // Realiza peticion al modelo, y el modelo ejecuta la funcion correspondiente
                val recetaId = dbHelper.crearReceta(nuevaReceta, ingredientes, pasos)
                if (recetaId != -1L) {
                    Toast.makeText(this, "Receta creada", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error al crear la receta", Toast.LENGTH_SHORT).show()
                }
                finish()
            }else{
                Toast.makeText(this, "Ingrese un nombre", Toast.LENGTH_SHORT).show()
            }
        }

    }
}