package com.example.magicgourmet.adapter

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.magicgourmet.R
import com.example.magicgourmet.model.DatabaseHelper
import com.example.magicgourmet.model.Paso
import com.example.magicgourmet.model.Receta
import com.example.magicgourmet.model.Usuario
import com.google.gson.Gson

class RecetaAdapter(
    private val context: Context,
    private val recetasConPasos: List<Pair<Receta, List<Paso>>>
) : RecyclerView.Adapter<RecetaAdapter.RecetaViewHolder>() {

    private val dbHelper: DatabaseHelper = DatabaseHelper(context)
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MiPreferencia", Context.MODE_PRIVATE)
    private val gson = Gson() // Inicializa Gson una sola vez

    class RecetaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.nombrecetaFeed)
        val tvDescripcion: TextView = itemView.findViewById(R.id.descrecetaFeed)
        val tvIngredientes: TextView = itemView.findViewById(R.id.ingredientesFeed)
        val tvPaso: TextView = itemView.findViewById(R.id.pasoapFeed)
        val tvLink: TextView = itemView.findViewById(R.id.linkFeed)
        val tvimagen: ImageView = itemView.findViewById(R.id.imagenrecetaFeed)
        val btnFavorito: Button = itemView.findViewById(R.id.btn_favorito)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecetaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_receta, parent, false)
        return RecetaViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecetaViewHolder, position: Int) {
        val (receta, pasos) = recetasConPasos[position]
        holder.tvNombre.text = receta.nombre
        holder.tvDescripcion.text = receta.descripcion
        holder.tvPaso.text = pasos.joinToString(", ") { it.descripcion }
        holder.tvIngredientes.text = receta.ingredientes
        holder.tvLink.text = receta.link

        // Cargar la imagen usando Glide
        Glide.with(holder.itemView.context)
            .load(receta.imagen)
            .placeholder(R.drawable.imagen_buscando)
            .error(R.drawable.imagen_nocargada)
            .into(holder.tvimagen)

        // Configurar el botón de favorito
        holder.btnFavorito.setOnClickListener {
            val userJson = sharedPreferences.getString("usuario_actual", null)
            val user = if (userJson != null) {
                gson.fromJson(userJson, Usuario::class.java)
            } else {
                null // Manejar el caso donde no hay usuario guardado
            }
            if (user != null) {
                dbHelper.agregarFavorito(receta.nombre, user.user) // Asegúrate de que `user.user` es el campo correcto
            } else {
                Toast.makeText(context, "Debe iniciar sesión", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return recetasConPasos.size
    }
}