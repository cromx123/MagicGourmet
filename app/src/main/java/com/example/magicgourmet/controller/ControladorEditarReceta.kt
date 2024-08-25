package com.example.magicgourmet.controller

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.bumptech.glide.Glide
import com.example.magicgourmet.R
import com.example.magicgourmet.model.DatabaseHelper
import com.example.magicgourmet.model.Paso
import com.example.magicgourmet.model.Receta


class ControladorEditarReceta: ComponentActivity() {
    private val SELECT_INGREDIENTS_REQUEST = 1
    private val SELECT_IMAGE_REQUEST = 2
    private var selectedIngredients: List<String> = listOf()
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_editar_receta)
        val btnVolver = findViewById<Button>(R.id.btnCancelmodReceta)
        val btnConfirmar = findViewById<Button>(R.id.btnAcceptmodReceta)
        val btnSelectIngredients = findViewById<Button>(R.id.btnSelectIngredientsMod)
        val btnagregarImagen = findViewById<Button>(R.id.btningresarI_recetaMod)
        val btnbuscarrecetaMod = findViewById<Button>(R.id.btn_buscarrecetaMod)
        val nombrereceta = findViewById<EditText>(R.id.nombrerecetamod)
        val linearbuscar = findViewById<LinearLayout>(R.id.btn_buscarreceta_modificar)
        val blockerview = findViewById<View>(R.id.blocker_view)

        // Extraer Nombre, Descripcion, Paso a paso, LInk
        val editTextNombre = findViewById<EditText>(R.id.nom_recetamod)
        val editTextDescrip = findViewById<EditText>(R.id.desc_recetamod)
        val editTextPaso = findViewById<EditText>(R.id.paso_recetamod)
        val editTextLink = findViewById<EditText>(R.id.link_videomod)
        val dbHelper = DatabaseHelper(this)
        // Cancelar la creacion de receta
        btnVolver.setOnClickListener {
            finish()
        }

        btnbuscarrecetaMod.setOnClickListener{
            val nombre = nombrereceta.text.toString()

            if (nombre.isNotEmpty()) {

                val buscarReceta = dbHelper.buscarReceta(nombre)
                Log.d("BuscarReceta", "Buscando receta con nombre: $buscarReceta")

                if (buscarReceta != null) {
                    blockerview.visibility = View.GONE
                    linearbuscar.visibility = View.GONE
                    findViewById<EditText>(R.id.nom_recetamod).setText(buscarReceta.nombre)
                    findViewById<EditText>(R.id.desc_recetamod).setText(buscarReceta.descripcion)
                    //findViewById<TextView>(R.id.ingredientesvisual).text = buscarReceta.ingredientes
                    findViewById<EditText>(R.id.link_videomod).setText(buscarReceta.link)

                } else {
                    Toast.makeText(this, "Error al buscar la receta", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Ingrese un nombre", Toast.LENGTH_SHORT).show()
            }
        }



        btnSelectIngredients.setOnClickListener {
            val intent = Intent(this, ControladorSelecIngredientes::class.java)
            startActivityForResult(intent, SELECT_INGREDIENTS_REQUEST)
        }
        btnagregarImagen.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            startActivityForResult(intent, SELECT_IMAGE_REQUEST)
        }
        // Insertar la receta en la base de datos
        btnConfirmar.setOnClickListener {
            // Obtener el texto ingresado por el usuario
            val nombre = editTextNombre.text.toString()
            val nombreantiguo = nombrereceta.text.toString()
            val descripcion = editTextDescrip.text.toString()
            val paso = editTextPaso.text.toString()
            val link = editTextLink.text.toString()
            if (nombre.isNotEmpty()) {
                // Se juntan los datos para poder
                val imagenRuta = imageUri?.toString() ?: ""
                val nuevaReceta = Receta(
                    nombre = nombre,
                    descripcion = descripcion,
                    ingredientes = selectedIngredients.joinToString(", "), // Puede ser un string separado por comas o puedes usar la lista de ingredientes
                    link = link,
                    imagen = imagenRuta
                )

                val pasos = Paso(
                    descripcion = paso
                )
                // Realiza peticion al modelo, y el modelo ejecuta la funcion correspondiente
                val recetaId = dbHelper.modificarReceta(nuevaReceta, pasos, nombreantiguo)
                if (recetaId != 0) {
                    Toast.makeText(this, "Receta editada", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Error al editar la receta", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Ingrese un nombre", Toast.LENGTH_SHORT).show()
            }
        }


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SELECT_INGREDIENTS_REQUEST && resultCode == Activity.RESULT_OK) {
            val ingredients = data?.getStringArrayListExtra("selectedIngredients")
            selectedIngredients = ingredients ?: listOf()
            Log.d("ControladorAnadirReceta", "Selected ingredients: ${selectedIngredients.joinToString(", ")}")
        }else if (requestCode == SELECT_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            imageUri = data?.data
            val imageView = findViewById<ImageView>(R.id.imagereceta)
            imageView?.setImageURI(imageUri)
        }

    }
}