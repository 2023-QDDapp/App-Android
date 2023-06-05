package com.example.qddapp.Retrofit

import android.content.Context
import com.example.qddapp.Modelos.ContinuarRegistroBody
import com.example.qddapp.Modelos.Evento
import com.example.qddapp.Modelos.EventoCrear
import com.example.qddapp.Modelos.RegistroBody

class Repositorio(context: Context) {
    private val retrofit = RetrofitHelper(context).getRetrofit()

    suspend fun login(email: String, pass: String) = retrofit.login(email, pass)

//    suspend fun registro(email: String, pass: String) = retrofit.registro(email, pass)

    suspend fun registroBody(registroBody: RegistroBody) = retrofit.registroBody(registroBody)


//    suspend fun continuarRegistro(
//        id: Int, nombre: String,
//        telefono: String,
//        fecha_nacimiento: String,
//        biografia: String,
//        foto: String,
//        categorias: IntArray
//    ) = retrofit.continuarRegistro(id, nombre, telefono, fecha_nacimiento, biografia, foto, categorias)

    suspend fun continuarRegistroBody(id: Int, continuarRegistroBody: ContinuarRegistroBody) = retrofit.continuarRegistroBody(id, continuarRegistroBody)

//    suspend fun crearEvento(
//        titulo: String,
//        fecha_hora_inicio: String,
//        fecha_hora_fin: String,
//        descripcion: String,
//        foto: String,
//        abierto: Boolean,
//        location: String,
//        latitud: String,
//        longitud: String,
//        categoria_id: Int
//    ) = retrofit.crearEvento(titulo, fecha_hora_inicio, fecha_hora_fin, descripcion, foto, abierto, location, latitud, longitud, categoria_id)

    suspend fun crearEventoBody(evento: EventoCrear) = retrofit.crearEventoBody(evento)

    suspend fun dameEventosFiltrados(
        categoria: String?,
        fecha_inicion: String?,
        fecha_fin: String?,
        location: String?,
        latitude: Double?,
        longitude: Double?
    ) = retrofit.dameEventosFiltrados(categoria, fecha_inicion, fecha_fin, location, latitude, longitude)

    suspend fun dameEventosParaMi(id: Int) = retrofit.dameEventosParaMi(id)

    suspend fun dameEventosSeguidos(id: Int) = retrofit.dameEventosSeguidos(id)

    suspend fun dameElUsuario(id: Int) = retrofit.dameElUsuario(id)

    suspend fun dameTodasCategorias() = retrofit.dameTodasCategorias()

    suspend fun dameElEvento(id: Int) = retrofit.dameElEvento(id)

    suspend fun dameMisSeguidos(id: Int) = retrofit.dameMisSeguidos(id)
}
