package com.example.qddapp.Modelos


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class EventoMisEventos(
    @SerializedName("edad")
    val edad: Int?,
    @SerializedName("eventos")
    val eventos: List<EventoMiEvento>?,
    @SerializedName("foto")
    val foto: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("organizador")
    val organizador: String?
) : Parcelable