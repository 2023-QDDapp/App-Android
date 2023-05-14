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
    @SerializedName("edad")
    val edad: Int,
    @SerializedName("foto")
    val foto: String,
    @SerializedName("biografia")
    val biografia: String,
    @SerializedName("categorias")
    val categorias: List<Categoria>
) : Parcelable
