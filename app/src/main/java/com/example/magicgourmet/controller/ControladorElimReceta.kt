package com.example.magicgourmet.controller

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import com.example.magicgourmet.R


class ControladorElimReceta: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_elim_receta)
        val btnVolver = findViewById<Button>(R.id.btnCancelelimReceta)
        val btnVolver2 = findViewById<Button>(R.id.btnCancelconelimReceta)
        val btnconfir = findViewById<Button>(R.id.btnAcceptelimReceta)
        val mensaje_confirmarelim =findViewById<FrameLayout>(R.id.mensaje_confirmarelim)

        btnVolver.setOnClickListener {
            finish()
        }
        btnVolver2.setOnClickListener {
            finish()
        }
        btnconfir.setOnClickListener{
            mensaje_confirmarelim.visibility = View.VISIBLE
        }
    }
}