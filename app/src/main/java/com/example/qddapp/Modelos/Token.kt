package com.example.qddapp.Modelos

import com.google.gson.annotations.SerializedName

data class Token(
    val token: String,
    val user: UsuarioToken
)

data class UsuarioToken(
    @SerializedName("id")
    val id: Int,
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("telefono")
    val telefono: String,
    @SerializedName("fecha_nacimiento")
    val fechaNacimiento: String,
    @SerializedName("biografia")
    val biografia: String,
    @SerializedName("foto")
    val foto: String
)
