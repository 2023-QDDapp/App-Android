package com.example.qddapp.Modelos


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class EventoMiEvento(
    @SerializedName("id_evento")
    val idEvento: Int?,
    @SerializedName("id_organizador")
    val idOrganizador: Int?,
    @SerializedName("organizador")
    val organizador: String?,
    @SerializedName("foto_organizador")
    val fotoOrganizador: String?,
    @SerializedName("edad")
    val edad: Int?,
    @SerializedName("titulo")
    val titulo: String?,
    @SerializedName("imagen_evento")
    val imagenEvento: String?,
    @SerializedName("fecha_hora_inicio")
    val fechaHoraInicio: String?,
    @SerializedName("fecha_hora_fin")
    val fechaHoraFin: String?,
    @SerializedName("categoria")
    val categoria: String?,
) : Parcelable