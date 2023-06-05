package com.example.qddapp.Modelos

data class Mensaje(
    var mensaje: String,
    var id: Int,
    var nombre: String,
    var foto: String,
    var email: String,
    var pasword: String,
    var fecha_nacimiento: String,
    var biografia: String,
    var intereses: List<Int>
)
