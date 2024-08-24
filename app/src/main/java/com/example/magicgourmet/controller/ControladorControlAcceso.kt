package com.example.magicgourmet.controller

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.magicgourmet.R
import com.example.magicgourmet.model.DatabaseHelper
import com.google.gson.Gson

class ControladorControlAcceso: ComponentActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_control_acceso)
        val btnRegistrar = findViewById<Button>(R.id.btn_registrarsecontrol)
        val btnIniciar = findViewById<Button>(R.id.btn_iniciar_session)
        val nombreusuario = findViewById<EditText>(R.id.controlaccesoUsuario)
        val correousuario = findViewById<EditText>(R.id.controlaccesoCorreo)
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val passusuario = findViewById<EditText>(R.id.controlaccesoContrasena)
        val dbHelper = DatabaseHelper(this)

        btnIniciar.setOnClickListener{
            val nombre = nombreusuario.text.toString()
            val correo = correousuario.text.toString()
            val pass = passusuario.text.toString()

            if (nombre.isNotEmpty() && correo.isNotEmpty() && pass.isNotEmpty()) {
                val buscarusuario = dbHelper.accesoUsuario(nombre, pass)
                Log.d("BuscarReceta", "Buscando receta con nombre: $buscarusuario")
                if (buscarusuario != null) {
                    sharedPreferences.edit().apply {
                        putInt("sessionActive", 1)
                        putString("name_user",buscarusuario.user)
                        putString("name_tip",buscarusuario.tipo_user)
                        apply()
                    }
                    val gson = Gson()
                    val userJson = gson.toJson(buscarusuario)
                    // Guardar el JSON en SharedPreferences
                    sharedPreferences.edit().apply {
                        putString("usuario_actual", userJson)
                        apply() // O usa commit() si quieres que se guarde de inmediato
                    }
                    startActivity(Intent(this, ControladorPerfilUsuario::class.java))
                    Toast.makeText(this, "Usuario encontrada", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error al buscar Usuario", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Ingrese un nombre o constraseña", Toast.LENGTH_SHORT).show()
            }

        }
        btnRegistrar.setOnClickListener{
            startActivity(Intent(this, ControladorAutoRegistro::class.java))
        }
        menuInferiorButtons()
    }

    private fun menuInferiorButtons() {

        val buttonActivityMap = mapOf(
            R.id.btnhomeaureg to ControladorPrincipal::class.java,
            R.id.btnbuscaraureg to ControladorBuscarReceta::class.java,
            R.id.btncrearaureg to ControladorAdminReceta::class.java,
            R.id.btnperfilaureg to ControladorPerfilUsuario::class.java
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