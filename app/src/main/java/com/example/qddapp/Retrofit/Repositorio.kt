package com.example.qddapp.Retrofit

class Repositorio {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun dameTodosEventos() = retrofit.dameTodosEventos()

    suspend fun dameEventosParaMi() = retrofit.dameEventosParaMi()

    suspend fun dameElUsuario() = retrofit.dameElUsuario()
}
