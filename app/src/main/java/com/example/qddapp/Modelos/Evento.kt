package com.example.qddapp.Modelos

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Evento(
    @SerializedName("id_evento")
    val idEvento: Int,
    @SerializedName("id_organizador")
    val idOrganizador: Int,
    @SerializedName("nombre")
    val name: String,
    @SerializedName("foto")
    val foto: String,
    @SerializedName("edad")
    val edad: Int,
    @SerializedName("imagen")
    val imagen: String?,
    @SerializedName("descripcion")
    val descripcion: String,
    @SerializedName("fecha_hora_inicio")
    val fechaHoraInicio: String,
    @SerializedName("Categoria")
    val categoria: String
) : Parcelable
