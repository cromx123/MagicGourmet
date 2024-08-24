package com.example.magicgourmet.controller

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import com.example.magicgourmet.R

class ControladorPrincipal : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_principal)
        val btnhome =findViewById<ImageButton>(R.id.btnhome)
        btnhome.setImageResource(R.drawable.hogar_selec)



        menuInferiorButtons()
    }

    private fun menuInferiorButtons() {

        val buttonActivityMap = mapOf(
            R.id.btnhome to ControladorPrincipal::class.java,
            R.id.btnbuscar to ControladorBuscarReceta::class.java,
            R.id.btncrear to ControladorAdminReceta::class.java,
            R.id.btnperfil to ControladorPerfilUsuario::class.java
        )


        buttonActivityMap.forEach { (buttonId, activityClass) ->
            findViewById<ImageButton>(buttonId).setOnClickListener {
                if (buttonId == R.id.btncrear) {
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
                                                                        