package com.example.magicgourmet.model

import java.io.Serializable

data class Receta(
    val nombre: String,
    val descripcion: String,
    val ingredientes: String,
    val link: String,
    val imagen: String
): Serializable
