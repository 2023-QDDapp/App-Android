package com.example.qddapp.Modelos


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class EventoMiEvento(
    @SerializedName("categoria")
    val categoria: String?,
    @SerializedName("descripcion")
    val descripcion: String?,
    @SerializedName("fecha_hora_inicio")
    val fechaHoraInicio: String?,
    @SerializedName("id_evento")
    val idEvento: Int?,
    @SerializedName("imagen_evento")
    val imagenEvento: String?,
    @SerializedName("titulo")
    val titulo: String?
) : Parcelable