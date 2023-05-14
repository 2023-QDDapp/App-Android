package com.example.qddapp.Retrofit

import com.example.qddapp.Modelos.Evento
import com.example.qddapp.Modelos.Usuario
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {
    @GET("/proyecto/api/events")
    suspend fun dameTodosEventos(): Response<List<Evento>>

    @GET("/proyecto/api/users/1/parati")
    suspend fun dameEventosParaMi(): Response<List<Evento>>

    @GET("/proyecto/api/users/1")
    suspend fun dameElUsuario(): Response<Usuario>
}