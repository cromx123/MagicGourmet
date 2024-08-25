package com.example.magicgourmet.controller

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.bumptech.glide.Glide
import com.example.magicgourmet.R
import com.example.magicgourmet.model.Receta

class ControladorRecetaVisualizada : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_receta_visualizada)

        val btnVolver = findViewById<Button>(R.id.btn_volver_menu)
        val btnComentario = findViewById<Button>(R.id.btn_comentario)
        val btnFavorito = findViewById<Button>(R.id.btn_favorito)
        val receta = intent.getSerializableExtra("NombreRecetab") as? Receta

        btnVolver.setOnClickListener {
            startActivity(Intent(this, ControladorPrincipal::class.java))
        }
        btnFavorito.setOnClickListener {
            startActivity(Intent(this, ControladorAgregarFavorito::class.java))
        }

        if (receta != null) {
            findViewById<TextView>(R.id.nombrerecetavisual).text = receta.nombre
            findViewById<TextView>(R.id.descrecetavisual).text = receta.descripcion
            findViewById<TextView>(R.id.ingredientesvisual).text = receta.ingredientes
            findViewById<TextView>(R.id.linkvisual).text = receta.link

            val imageView = findViewById<ImageView>(R.id.imagenrecetaVisual)


            Glide.with(this)
                    .load(receta.imagen)
                    .error(R.drawable.imagen_nocargada) // Imagen por defecto en caso de error
                    .into(imageView)
        } else {
            Toast.makeText(this, "No se pudo cargar la receta", Toast.LENGTH_SHORT).show()
        }
    }
}