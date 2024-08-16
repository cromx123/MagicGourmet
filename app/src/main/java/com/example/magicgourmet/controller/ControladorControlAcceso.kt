package com.example.magicgourmet.controller

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.example.magicgourmet.R

class ControladorControlAcceso: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_control_acceso)
        val btnRegistrar = findViewById<Button>(R.id.btn_registrarsecontrol)
        val btnIniciar = findViewById<Button>(R.id.btn_iniciar_session)

        btnRegistrar.setOnClickListener{
            startActivity(Intent(this, ControladorAutoRegistro::class.java))
        }
        btnIniciar.setOnClickListener{

            finish()
        }
    }
}