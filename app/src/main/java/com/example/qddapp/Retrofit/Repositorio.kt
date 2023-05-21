package com.example.qddapp.Retrofit

class Repositorio {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun dameTodosEventos() = retrofit.dameTodosEventos()

    suspend fun dameEventosParaMi(id: Int) = retrofit.dameEventosParaMi(id)

    suspend fun dameEventosSeguidos(id: Int) = retrofit.dameEventosSeguidos(id)

    suspend fun dameElUsuario(id: Int) = retrofit.dameElUsuario(id)

    suspend fun dameTodasCategorias() = retrofit.dameTodasCategorias()

    suspend fun dameElEvento(id: Int) = retrofit.dameElEvento(id)
}
