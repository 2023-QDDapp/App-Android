package com.example.qddapp.Modelos

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Categoria(
    @SerializedName("id")
    var id: Int,
    @SerializedName("categoria")
    var categoria: String
) : Parcelable
