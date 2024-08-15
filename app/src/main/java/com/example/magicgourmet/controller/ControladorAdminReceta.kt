package com.example.magicgourmet.controller

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import com.example.magicgourmet.R

class ControladorAdminReceta: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_admin_receta)

        val receivedText = intent.getStringExtra("OpcionAdmReceta")
        if (receivedText == "crear"){
            startActivity(Intent(this, ControladorAnadirReceta::class.java))
        }
        val btncrearir =findViewById<ImageButton>(R.id.btncrearir)
        btncrearir.setImageResource(R.drawable.agregar_selec)

        menuCenterButtons()
        menuInferiorButtons()

    }

    private fun menuInferiorButtons() {
        val buttonActivityMap = mapOf(
            R.id.btnhomeir to ControladorPrincipal::class.java,
            R.id.btnbuscarir to ControladorBuscarReceta::class.java,
            R.id.btncrearir to ControladorAdminReceta::class.java,
            R.id.btnperfilir to ControladorVisFavorito::class.java,
        )

        buttonActivityMap.forEach { (buttonId, activityClass) ->
            findViewById<ImageButton>(buttonId).setOnClickListener {
                startActivity(Intent(this, activityClass))

            }
        }
    }
    private fun menuCenterButtons() {
        val buttonActivityMap = mapOf(
            R.id.btn_controlcrear to ControladorAnadirReceta::class.java,
            R.id.btn_controlmod to ControladorEditarReceta::class.java,
            R.id.btn_controlelim to ControladorElimReceta::class.java,
        )

        buttonActivityMap.forEach { (buttonId, activityClass) ->
            findViewById<Button>(buttonId).setOnClickListener {
                startActivity(Intent(this, activityClass))

            }
        }
    }
}