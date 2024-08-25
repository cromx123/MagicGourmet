package com.example.magicgourmet.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.magicgourmet.R
import com.example.magicgourmet.model.Receta

class RecetaAdapter(private val recetaList: List<Receta>) : RecyclerView.Adapter<RecetaAdapter.RecetaViewHolder>() {

    class RecetaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.nombrecetaFeed)
        val tvDescripcion: TextView = itemView.findViewById(R.id.descrecetaFeed)
        val tvIngredientes: TextView = itemView.findViewById(R.id.ingredientesFeed)
        val tvLink: TextView = itemView.findViewById(R.id.linkFeed)
        val tvimagen: ImageView = itemView.findViewById(R.id.imagenrecetaFeed)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecetaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_receta, parent, false)
        return RecetaViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecetaViewHolder, position: Int) {
        val receta = recetaList[position]
        holder.tvNombre.text = receta.nombre
        holder.tvDescripcion.text = receta.descripcion
        holder.tvIngredientes.text = receta.ingredientes
        holder.tvLink.text = receta.link

        // log. Cargar la imagen usando Glide
        Glide.with(holder.itemView.context)
            .load(receta.imagen) // Aquí se carga la imagen desde la ruta o URI

            .placeholder(R.drawable.imagen_buscando) // Imagen de placeholder mientras carga
            .error(R.drawable.imagen_nocargada) // Imagen en caso de error
            .into(holder.tvimagen)
    }

    override fun getItemCount(): Int {
        return recetaList.size
    }
}