package com.example.magicgourmet.controller

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.magicgourmet.R
import com.example.magicgourmet.adapter.RecetaAdapter
import com.example.magicgourmet.model.DatabaseHelper


class ControladorVisFavorito: ComponentActivity() {
    private lateinit var dbHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_vis_favorito)

        val btnperfilper =findViewById<ImageButton>(R.id.btnperfilper)
        btnperfilper.setImageResource(R.drawable.perfil_us_selec)

        dbHelper = DatabaseHelper(this)
        val recetasConPasos = dbHelper.obtenerRecetasConPasos()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerviewFavoritos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecetaAdapter(this,recetasConPasos)


        menuInferiorButtons()
    }

    private fun menuInferiorButtons() {

        val buttonActivityMap = mapOf(
            R.id.btnhomeper to ControladorPrincipal::class.java,
            R.id.btnbuscarper to ControladorBuscarReceta::class.java,
            R.id.btncrearper to ControladorAdminReceta::class.java,
            R.id.btnperfilper to ControladorVisFavorito::class.java
        )


        buttonActivityMap.forEach { (buttonId, activityClass) ->
            findViewById<ImageButton>(buttonId).setOnClickListener {
                if (buttonId == R.id.btncrearper) {
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