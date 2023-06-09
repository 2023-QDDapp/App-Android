package com.example.qddapp.Modelos

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Usuario(
    @SerializedName("id")
    val id: Int,
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("foto")
    val foto: String,
    @SerializedName("edad")
    val edad: Int,
    @SerializedName("biografia")
    val biografia: String,
    @SerializedName("intereses")
    val intereses: List<Categoria>,
    @SerializedName("valoraciones")
    val valoraciones: List<Valoracion>
) : Parcelable
