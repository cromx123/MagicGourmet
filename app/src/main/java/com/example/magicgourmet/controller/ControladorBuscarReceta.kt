package com.example.magicgourmet.controller

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import com.example.magicgourmet.R


class ControladorBuscarReceta: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_buscar_receta)
        val btnbuscarbr =findViewById<ImageButton>(R.id.btnbuscarbr)
        btnbuscarbr.setImageResource(R.drawable.busqueda_selec)
        menuInferiorButtons()
    }

    private fun menuInferiorButtons() {

        val buttonActivityMap = mapOf(
            R.id.btnhomebr to ControladorPrincipal::class.java,
            R.id.btnbuscarbr to ControladorBuscarReceta::class.java,
            R.id.btncrearbr to ControladorAdminReceta::class.java,
            R.id.btnperfilbr to ControladorVisFavorito::class.java
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