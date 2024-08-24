package com.example.magicgourmet.controller

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import com.example.magicgourmet.R

class ControladorAdminFiltro: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_admin_filtro)

        menuInferiorButtons()
    }
    private fun menuInferiorButtons() {
        val buttonActivityMap = mapOf(
            R.id.btnhome_filter to ControladorPrincipal::class.java,
            R.id.btnbuscar_filter to ControladorBuscarReceta::class.java,
            R.id.btncrear_filter to ControladorAdminReceta::class.java,
            R.id.btnperfil_filter to ControladorPerfilUsuario::class.java,
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