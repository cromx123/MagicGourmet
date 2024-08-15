package com.example.magicgourmet.controller

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import com.example.magicgourmet.R


class ControladorAnadirReceta: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_ingresar_receta)
        val btnVolver = findViewById<Button>(R.id.btnCancelReceta)

        btnVolver.setOnClickListener {
            finish()
        }
    }
}