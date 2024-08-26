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
import com.example.magicgourmet.model.Paso
import com.example.magicgourmet.model.Receta

class ControladorRecetaVisualizada : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_receta_visualizada)

        val btnVolver = findViewById<Button>(R.id.btn_volver_menu)
        val btnComentario = findViewById<Button>(R.id.btn_comentario)
        val btnFavorito = findViewById<Button>(R.id.btn_favorito)
        val nombreReceta = intent.getStringExtra("NombreReceta")
        val descripcionReceta = intent.getStringExtra("DescripcionReceta")
        val ingredientesReceta = intent.getStringExtra("IngredientesReceta")
        val linkReceta = intent.getStringExtra("LinkReceta")
        val imagenReceta = intent.getStringExtra("ImagenReceta")
        val pasosReceta = intent.getStringExtra("PasosReceta")

        btnVolver.setOnClickListener {
            startActivity(Intent(this, ControladorPrincipal::class.java))
        }
        btnFavorito.setOnClickListener {
            startActivity(Intent(this, ControladorAgregarFavorito::class.java))
        }

        if (nombreReceta != null) {
            findViewById<TextView>(R.id.nombrerecetavisual).text = nombreReceta
            findViewById<TextView>(R.id.descrecetavisual).text = descripcionReceta
            findViewById<TextView>(R.id.ingredientesvisual).text = ingredientesReceta
            findViewById<TextView>(R.id.pasoapvisual).text = pasosReceta
            findViewById<TextView>(R.id.linkvisual).text = linkReceta

            val imageView = findViewById<ImageView>(R.id.imagenrecetaVisual)


            Glide.with(this)
                    .load(imagenReceta)
                    .error(R.drawable.imagen_nocargada) // Imagen por defecto en caso de error
                    .into(imageView)
        } else {
            Toast.makeText(this, "No se pudo cargar la receta", Toast.LENGTH_SHORT).show()
        }
    }
}