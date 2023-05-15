package com.example.qddapp.Modelos

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Valoracion(
    @SerializedName("id_usuario")
    val id_usuario: Int,
    @SerializedName("nombre_usuario")
    val nombre_usuario: String,
    @SerializedName("foto")
    val foto: String,
    @SerializedName("mensaje")
    val mensaje: String
) : Parcelable
