package com.example.magicgourmet.model

import java.io.Serializable

data class Usuario (
    val user: String,
    val pass: String,
    val correo: String,
    val tipo_user: String
): Serializable
