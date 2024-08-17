package com.example.magicgourmet.controller

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.magicgourmet.R

class ControladorPerfilUsuario: ComponentActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private var sessionActive: Int = 0
    private var username: String?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_perfil)
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // Recuperar el inicio de seccion desde SharedPreferences
        sessionActive = sharedPreferences.getInt("sessionActive", 0)
        sessionActive =0
        if (sessionActive == 0) {
            startActivity(Intent(this, ControladorControlAcceso::class.java))
        }
        val btnbuscarper =findViewById<ImageButton>(R.id.btnperfilper)
        btnbuscarper.setImageResource(R.drawable.perfil_us_selec)

        val userName_perfil = findViewById<TextView>(R.id.userName_perfil)
        username = sharedPreferences.getString("nameuser", null)
        if (username != null){
            userName_perfil.text = username
        }
        menuInferiorButtons()
        menuCentralAdmin()
    }

    private fun menuCentralAdmin() {

        val buttonActivityMap = mapOf(
            R.id.btn_adm_ingre to ControladorAdminIngrediente::class.java,
            R.id.btn_adm_receta to ControladorAdminReceta::class.java,
            R.id.btn_adm_usuario to ControladorAdminUsuario::class.java,
            R.id.btn_adm_filtro to ControladorAdminFiltro::class.java,
            R.id.btn_adm_comentario to ControladorAdminComentario::class.java
        )

        buttonActivityMap.forEach { (buttonId, activityClass) ->
            findViewById<ImageButton>(buttonId).setOnClickListener {
                startActivity(Intent(this, activityClass))
            }
        }
    }
    private fun menuInferiorButtons() {

        val buttonActivityMap = mapOf(
            R.id.btnhomeper to ControladorPrincipal::class.java,
            R.id.btnbuscarper to ControladorBuscarReceta::class.java,
            R.id.btncrearper to ControladorAdminReceta::class.java,
            R.id.btnperfilper to ControladorPerfilUsuario::class.java
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