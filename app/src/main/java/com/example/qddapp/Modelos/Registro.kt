package com.example.qddapp.Modelos

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import retrofit2.http.Path
import retrofit2.http.Query

@Parcelize
data class Registro(
    @SerializedName("mensaje")
    val mensaje: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
): Parcelable

@Parcelize
data class RegistroBody(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
): Parcelable

@Parcelize
data class ContinuarRegistroBody(
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("telefono")
    val telefono: String,
    @SerializedName("fecha_nacimiento")
    val fecha_nacimiento: String,
    @SerializedName("biografia")
    val biografia: String,
    @SerializedName("foto")
    val foto: String,
    @SerializedName("categorias")
    val categorias: IntArray
): Parcelable