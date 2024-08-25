package com.example.magicgourmet.controller

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.magicgourmet.R
import com.example.magicgourmet.model.Usuario
import com.google.gson.Gson


class ControladorPerfilUsuario: ComponentActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private var sessionActive: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_perfil)
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        //val user = intent.getSerializableExtra("usuariovip") as? Usuario
        // Obtener el JSON del usuario guardado
        val userJson = sharedPreferences.getString("usuario_actual", null)
        val gson = Gson()
        val user = if (userJson != null) {
            gson.fromJson(userJson, Usuario::class.java)
        } else {
            null // Manejar el caso donde no hay usuario guardado
        }
        sessionActive = sharedPreferences.getInt("sessionActive", 0)
        if (sessionActive == 1 && user != null) {
            // Aquí puedes usar los datos del usuario
            findViewById<TextView>(R.id.userName_perfil).text = user.user
            if (user.tipo_user == "Administrador") {
                findViewById<TextView>(R.id.perfiladm_text).visibility = View.VISIBLE
                findViewById<LinearLayout>(R.id.perfiladm_lista).visibility = View.VISIBLE
            }
            if (user.tipo_user == "Cliente") {
                findViewById<LinearLayout>(R.id.perfilcli_lista).visibility = View.VISIBLE
            }
        } else {
            Toast.makeText(this, "No se pudo cargar el usuario o sesión no iniciada", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ControladorControlAcceso::class.java))
        }

        val btnbuscarper =findViewById<ImageButton>(R.id.btnperfilper)
        val btncerrarsession =findViewById<Button>(R.id.btnAcceptconCerrar)
        val btncancelar =findViewById<Button>(R.id.btnCancelconCerrar)
        val mensaje = findViewById<FrameLayout>(R.id.mensaje_confirmarcerrar)
        btnbuscarper.setImageResource(R.drawable.letrax_select)
        btnbuscarper.setOnClickListener{
            mensaje.visibility = View.VISIBLE
        }
        btncancelar.setOnClickListener{
            mensaje.visibility = View.GONE
        }
        btncerrarsession.setOnClickListener{
            sharedPreferences.edit().apply {
                putInt("sessionActive", 0)
                apply()
            }
            startActivity(Intent(this, ControladorPrincipal::class.java))
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
            findViewById<Button>(buttonId).setOnClickListener {
                startActivity(Intent(this, activityClass))
            }
        }
    }

    private fun menuInferiorButtons() {

        val buttonActivityMap = mapOf(
            R.id.btnhomeper to ControladorPrincipal::class.java,
            R.id.btnbuscarper to ControladorBuscarReceta::class.java,
            R.id.btncrearper to ControladorAnadirReceta::class.java
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