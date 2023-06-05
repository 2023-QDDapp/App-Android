package com.example.qddapp.Modelos


import com.google.gson.annotations.SerializedName

data class MensajeEvento(
    @SerializedName("descripcion")
    val descripcion: String,
    @SerializedName("fecha_hora_fin")
    val fechaHoraFin: String,
    @SerializedName("fecha_hora_inicio")
    val fechaHoraInicio: String,
    @SerializedName("id_categoria")
    val idCategoria: Int,
    @SerializedName("id_evento")
    val idEvento: Int,
    @SerializedName("id_organizador")
    val idOrganizador: Int,
    @SerializedName("imagen_evento")
    val imagenEvento: String,
    @SerializedName("latitud")
    val latitud: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("longitud")
    val longitud: String,
    @SerializedName("mensaje")
    val mensaje: String,
    @SerializedName("tipo")
    val tipo: Boolean,
    @SerializedName("titulo")
    val titulo: String
)