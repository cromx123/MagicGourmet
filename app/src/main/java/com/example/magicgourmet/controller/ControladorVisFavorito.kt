package com.example.magicgourmet.controller

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import com.example.magicgourmet.R


class ControladorVisFavorito: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_vis_favorito)

        val btnperfilper =findViewById<ImageButton>(R.id.btnperfilper)
        btnperfilper.setImageResource(R.drawable.perfil_us_selec)

        menuInferiorButtons()
    }

    private fun menuInferiorButtons() {

        val buttonActivityMap = mapOf(
            R.id.btnhomeper to ControladorPrincipal::class.java,
            R.id.btnbuscarper to ControladorBuscarReceta::class.java,
            R.id.btncrearper to ControladorAdminReceta::class.java,
            R.id.btnperfilper to ControladorVisFavorito::class.java
        )


        buttonActivityMap.forEach { (buttonId, activityClass) ->
            findViewById<ImageButton>(buttonId).setOnClickListener {
                if (buttonId == R.id.btncrearper) {
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