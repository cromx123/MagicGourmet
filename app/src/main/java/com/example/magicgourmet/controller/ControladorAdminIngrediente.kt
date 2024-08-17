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
}