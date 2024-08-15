package com.example.magicgourmet.controller

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import com.example.magicgourmet.R

class ControladorPrincipal : ComponentActivity() {
    private lateinit var btnhome: Button
    private lateinit var btnbuscar: Button
    private lateinit var btncrear: Button
    private lateinit var btnperfil: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_principal)

        btnbuscar.setOnClickListener {
            val optionsIntent = Intent(this, ControladorBuscarReceta::class.java)
            startActivity(optionsIntent)
        }
        btncrear.setOnClickListener {
            val optionsIntent = Intent(this, ControladorAnadirReceta::class.java)
            startActivity(optionsIntent)
        }
        btnperfil.setOnClickListener {
            val optionsIntent = Intent(this, ControladorVisFavorito::class.java)
            startActivity(optionsIntent)
        }
    }
}
                                                                        