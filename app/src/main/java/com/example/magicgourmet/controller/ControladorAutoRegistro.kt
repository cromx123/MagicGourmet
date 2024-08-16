package com.example.magicgourmet.controller

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.magicgourmet.R
import com.example.magicgourmet.model.DatabaseHelper
import com.example.magicgourmet.model.Usuario

class ControladorAutoRegistro: ComponentActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_autoregistro)
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val btnVolver = findViewById<Button>(R.id.btn_salirregistro)
        val btnRegistro= findViewById<Button>(R.id.btn_registrarse)
        val editTextNombre = findViewById<EditText>(R.id.autoregistusuario)
        val editTextCorreo = findViewById<EditText>(R.id.autoregistcorreo)
        val editTextContrasena = findViewById<EditText>(R.id.autoregistcontrasena)
        val editTextConfcontrasena = findViewById<EditText>(R.id.autoregistcontrasena2)
        val dbHelper = DatabaseHelper(this)

        // Cancelar la creacion de receta
        btnVolver.setOnClickListener {
            finish()
        }
        btnRegistro.setOnClickListener{
            val nombre = editTextNombre.text.toString()
            val correo = editTextCorreo.text.toString()
            val pass = editTextContrasena.text.toString()
            val confirmarpass = editTextConfcontrasena.text.toString()
            if(nombre.isNotEmpty() && correo.isNotEmpty() && pass.isNotEmpty()){
                if (pass == confirmarpass){
                    // Preparación de datos para el envio
                    val nuevoUsuario = Usuario(
                        user = nombre,
                        pass = pass,
                        correo = correo,
                        tipo_user = "Cliente"
                    )
                    val usuarioId = dbHelper.crearUsuario(nuevoUsuario)
                    if (usuarioId != -1L) {
                        Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show()
                        sharedPreferences.edit().apply {
                            putString("nameuser", nombre)
                            putInt("sessionActive", 1)
                            apply() // Aplica los cambios de una sola vez
                        }
                    } else {
                        Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show()
                    }
                    finish()
                }else{
                    Toast.makeText(this,"No coinciden las contraseñas",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this,"Falta completar un campo", Toast.LENGTH_LONG).show()
            }
        }
    }
}