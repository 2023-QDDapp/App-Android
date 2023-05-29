package com.example.qddapp.Retrofit

import com.example.qddapp.Modelos.Categoria
import com.example.qddapp.Modelos.Evento
import com.example.qddapp.Modelos.Token
import com.example.qddapp.Modelos.Usuario
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @Headers("Accept: application/json")
    @POST("/proyecto/api/loginApi")
    suspend fun login(
        @Query("email") email: String,
        @Query("password") password: String
    ): Response<Token>

    @GET("/proyecto/api/events")
    suspend fun dameTodosEventos(): Response<List<Evento>>

    @GET("/proyecto/api/users/{id}/parati")
    suspend fun dameEventosParaMi(@Path("id") id: Int): Response<List<Evento>>

    @GET("/proyecto/api/users/{id}/pantallaseguidos")
    suspend fun dameEventosSeguidos(@Path("id") id: Int): Response<List<Evento>>

    @GET("/proyecto/api/users/{id}")
    suspend fun dameElUsuario(@Path("id") id: Int): Response<Usuario>

    @GET("/proyecto/api/categorias")
    suspend fun dameTodasCategorias(): Response<List<Categoria>>

    @GET("/proyecto/api/events/{id}")
    suspend fun dameElEvento(@Path("id") id: Int): Response<Evento>
}