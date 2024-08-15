package com.example.magicgourmet.controller

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.example.magicgourmet.R


class ControladorEditarReceta: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_editar_receta)
        val btnVolver = findViewById<Button>(R.id.btnCancelmodReceta)
        btnVolver.setOnClickListener {
            finish()
        }

    }
}