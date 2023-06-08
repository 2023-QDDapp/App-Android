package com.example.qddapp.Modelos

data class EventoCrear(
    val titulo: String,
    val fecha_hora_inicio: String,
    val fecha_hora_fin: String,
    val descripcion: String,
    val imagen: String,
    val tipo: Boolean,
    val location: String,
    val latitud: String,
    val longitud: String,
    val categoria_id: Int
)