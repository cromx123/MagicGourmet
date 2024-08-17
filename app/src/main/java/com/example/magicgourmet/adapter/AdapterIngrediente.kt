package com.example.magicgourmet.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.magicgourmet.R

class AdapterIngrediente(private val ingredients: List<String>) : RecyclerView.Adapter<ViewHolderIngrediente>() {
    private val selectedIngredients = mutableListOf<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderIngrediente {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.listaingredientes, parent, false)
        return ViewHolderIngrediente(view)

    }

    override fun onBindViewHolder(holder: ViewHolderIngrediente, position: Int) {
        val ingredient = ingredients[position]
        holder.checkBoxIngredient.text = ingredient
        holder.checkBoxIngredient.setOnCheckedChangeListener(null) // Evitar reciclaje incorrecto
        holder.checkBoxIngredient.isChecked = selectedIngredients.contains(ingredient)

        holder.checkBoxIngredient.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedIngredients.add(ingredient)
            } else {
                selectedIngredients.remove(ingredient)
            }
        }
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }

    fun getSelectedIngredients(): List<String> {
        return selectedIngredients
    }
}