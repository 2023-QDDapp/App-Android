package com.example.qddapp.Modelos

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.sql.Date
import java.time.format.DateTimeFormatter

@Parcelize
data class Evento(
    @SerializedName("id_evento")
    val idEvento: Int,
    @SerializedName("organizador")
    val organizador: String?,
    @SerializedName("id_organizador")
    val idOrganizador: Int,
    @SerializedName("foto_organizador")
    val fotoOrganizador: String,
    @SerializedName("edad")
    val edad: Int,
    @SerializedName("descripcion")
    val descripcion: String,
    @SerializedName("imagen_evento")
    val imagenEvento: String?,
    @SerializedName("titulo")
    val titulo: String,
    @SerializedName("fecha_hora_inicio")
    val fechaHoraInicio: String,
    @SerializedName("fecha_hora_fin")
    val fechaHoraFin: String,
    @SerializedName("location")
    val location: String?,
    @SerializedName("latitud")
    val latitud: Double,
    @SerializedName("longitud")
    val longitud: Double,
    @SerializedName("id_categoria")
    val idCategoria: Int,
    @SerializedName("categoria")
    val categoria: String,
    @SerializedName("num_participantes")
    val num_participantes: Int?,
    @SerializedName("asistentes")
    val asistentes: List<Asistente>?
) : Parcelable