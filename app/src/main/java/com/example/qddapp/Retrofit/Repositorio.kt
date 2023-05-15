package com.example.qddapp.Retrofit

class Repositorio {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun dameTodosEventos() = retrofit.dameTodosEventos()

    suspend fun dameEventosParaMi() = retrofit.dameEventosParaMi()

    suspend fun dameEventosSeguidos() = retrofit.dameEventosSeguidos()

    suspend fun dameElUsuario() = retrofit.dameElUsuario()

    suspend fun dameTodasCategorias() = retrofit.dameTodasCategorias()

    suspend fun dameElEvento() = retrofit.dameElEvento()
}
