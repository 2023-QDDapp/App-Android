package com.example.qddapp.Retrofit

import android.content.Context

class Repositorio(context: Context) {
    private val retrofit = RetrofitHelper(context).getRetrofit()

    suspend fun login(email: String, pass: String) = retrofit.login(email, pass)

    suspend fun dameTodosEventos() = retrofit.dameTodosEventos()

    suspend fun dameEventosParaMi(id: Int) = retrofit.dameEventosParaMi(id)

    suspend fun dameEventosSeguidos(id: Int) = retrofit.dameEventosSeguidos(id)

    suspend fun dameElUsuario(id: Int) = retrofit.dameElUsuario(id)

    suspend fun dameTodasCategorias() = retrofit.dameTodasCategorias()

    suspend fun dameElEvento(id: Int) = retrofit.dameElEvento(id)
}
