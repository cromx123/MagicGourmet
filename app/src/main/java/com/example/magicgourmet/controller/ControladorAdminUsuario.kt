package com.example.magicgourmet.controller

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import com.example.magicgourmet.R

class ControladorAdminUsuario: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_admin_usuario)

        menuInferiorButtons()
    }
    private fun menuInferiorButtons() {
        val buttonActivityMap = mapOf(
            R.id.btnhome_adm_us to ControladorPrincipal::class.java,
            R.id.btnbuscar_adm_us to ControladorBuscarReceta::class.java,
            R.id.btncrear_adm_us to ControladorAdminReceta::class.java,
            R.id.btnperfil_adm_us to ControladorPerfilUsuario::class.java,
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