package com.example.magicgourmet.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.magicgourmet.R
import com.example.magicgourmet.model.Receta

class RecetaAdapter(private val recetaList: List<Receta>) : RecyclerView.Adapter<RecetaAdapter.RecetaViewHolder>() {

    class RecetaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.nombrecetaFeed)
        val tvDescripcion: TextView = itemView.findViewById(R.id.descrecetaFeed)
        val tvIngredientes: TextView = itemView.findViewById(R.id.ingredientesFeed)
        val tvLink: TextView = itemView.findViewById(R.id.linkFeed)
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
    }

    override fun getItemCount(): Int {
        return recetaList.size
    }
}