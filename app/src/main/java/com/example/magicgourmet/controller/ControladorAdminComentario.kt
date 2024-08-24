package com.example.magicgourmet.controller


import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.example.magicgourmet.R

class ControladorAdminComentario:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_admin_comentario)

        val btnCancelarIng = findViewById<Button>(R.id.btn_voler_admc)
        btnCancelarIng.setOnClickListener {
            finish()
        }
    }

}