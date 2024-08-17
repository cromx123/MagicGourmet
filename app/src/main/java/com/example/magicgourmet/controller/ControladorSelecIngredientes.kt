package com.example.magicgourmet.controller

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.magicgourmet.R
import com.example.magicgourmet.adapter.AdapterIngrediente
import com.example.magicgourmet.model.DatabaseHelper

class ControladorSelecIngredientes: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_seleccionar_ingredientes)

        // Configura el RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewIngredients)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Cargar ingredientes desde la base de datos
        val dbHelper = DatabaseHelper(this)
        val ingredients = dbHelper.getAllIngredients()
        Log.d("SelectIngredientsActivity", "Ingredients asd loaded: ${ingredients.size}")

        // Configurar el adapter
        val adapter = AdapterIngrediente(ingredients)
        recyclerView.adapter = adapter

        // Configurar el botón de confirmación
        val buttonConfirmSelection: Button = findViewById(R.id.buttonConfirmSelection)
        buttonConfirmSelection.setOnClickListener {
            val selectedIngredients = adapter.getSelectedIngredients()

            val resultIntent = Intent().apply {
                putStringArrayListExtra("selectedIngredients", ArrayList(selectedIngredients))
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}