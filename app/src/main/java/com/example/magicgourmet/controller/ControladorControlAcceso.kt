package com.example.magicgourmet.controller

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import com.example.magicgourmet.R

class ControladorControlAcceso: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_control_acceso)
        val btnRegistrar = findViewById<Button>(R.id.btn_registrarsecontrol)
        val btnIniciar = findViewById<Button>(R.id.btn_iniciar_session)

        btnRegistrar.setOnClickListener{
            startActivity(Intent(this, ControladorAutoRegistro::class.java))
        }
        btnIniciar.setOnClickListener{

            finish()
        }
        menuInferiorButtons()
    }

    private fun menuInferiorButtons() {

        val buttonActivityMap = mapOf(
            R.id.btnhomeaureg to ControladorPrincipal::class.java,
            R.id.btnbuscaraureg to ControladorBuscarReceta::class.java,
            R.id.btncrearaureg to ControladorAdminReceta::class.java,
            R.id.btnperfilaureg to ControladorPerfilUsuario::class.java
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