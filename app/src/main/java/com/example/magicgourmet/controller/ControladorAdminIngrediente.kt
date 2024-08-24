package com.example.magicgourmet.controller

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import com.example.magicgourmet.R

class ControladorAdminIngrediente:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_admin_ingrediente)

        menuCentralAdmin()
        menuInferiorButtons()
    }
    private fun menuCentralAdmin() {

        val buttonActivityMap = mapOf(
            R.id.btn_anadir_ing to ControladorAnadirIngrediente::class.java,
            R.id.btn_elim_ing to ControladorElimIngrediente::class.java
        )

        buttonActivityMap.forEach { (buttonId, activityClass) ->
            findViewById<Button>(buttonId).setOnClickListener {
                startActivity(Intent(this, activityClass))
            }
        }
    }
    private fun menuInferiorButtons() {
        val buttonActivityMap = mapOf(
            R.id.btnhome_adm_ing to ControladorPrincipal::class.java,
            R.id.btnbuscar_adm_ing to ControladorBuscarReceta::class.java,
            R.id.btncrear_adm_ing to ControladorAdminReceta::class.java,
            R.id.btnperfil_adm_ing to ControladorPerfilUsuario::class.java,
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