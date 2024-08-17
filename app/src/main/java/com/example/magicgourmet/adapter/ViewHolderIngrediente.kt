package com.example.magicgourmet.adapter

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.magicgourmet.R

class ViewHolderIngrediente(itemView: View) :  RecyclerView.ViewHolder(itemView){
    val checkBoxIngredient: CheckBox = itemView.findViewById(R.id.checkBoxIngredient)
}