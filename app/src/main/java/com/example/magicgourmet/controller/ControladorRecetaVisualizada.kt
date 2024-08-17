package com.example.magicgourmet.controller

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.magicgourmet.R
import com.example.magicgourmet.model.Receta


class ControladorRecetaVisualizada: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_receta_visualizada)
        val btnVolver =findViewById<Button>(R.id.btn_volver_menu)
        val btnComentario =findViewById<Button>(R.id.btn_comentario)
        val btnFavorito =findViewById<Button>(R.id.btn_favorito)
        val receta = intent.getSerializableExtra("NombreRecetab") as? Receta

        btnVolver.setOnClickListener{
            startActivity(Intent(this, ControladorPrincipal::class.java))
        }
        btnFavorito.setOnClickListener{
            startActivity(Intent(this, ControladorAgregarFavorito::class.java))
        }
        if (receta != null) {
            // Aquí puedes usar los datos de la receta
            findViewById<TextView>(R.id.nombrerecetavisual).text = receta.nombre
            findViewById<TextView>(R.id.descrecetavisual).text = receta.descripcion
            findViewById<TextView>(R.id.ingredientesvisual).text = receta.ingredientes
            //findViewById<TextView>(R.id.pasoapvisual).text = receta.paso
            findViewById<TextView>(R.id.linkvisual).text = receta.link

        } else {
            Toast.makeText(this, "No se pudo cargar la receta", Toast.LENGTH_SHORT).show()
        }


    }
}