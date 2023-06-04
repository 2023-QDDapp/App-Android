package com.example.qddapp.Retrofit

import com.example.qddapp.Modelos.Asistente
import com.example.qddapp.Modelos.Categoria
import com.example.qddapp.Modelos.ContinuarRegistroBody
import com.example.qddapp.Modelos.Evento
import com.example.qddapp.Modelos.Mensaje
import com.example.qddapp.Modelos.Registro
import com.example.qddapp.Modelos.RegistroBody
import com.example.qddapp.Modelos.Token
import com.example.qddapp.Modelos.Usuario
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
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

//    @Headers("Accept: application/json")
//    @POST ("/proyecto/api/register")
//    suspend fun registro(
//        @Query("email") email: String,
//        @Query("password") password: String
//    ): Response<Registro>

    @Headers("Accept: application/json")
    @POST ("/proyecto/api/register")
    suspend fun registroBody(
        @Body registro: RegistroBody
    ): Response<Registro>

    @Headers("Accept: application/json")
    @POST("/proyecto/api/continue/register/{id}")
    suspend fun continuarRegistro(
        @Path("id") id: Int,
        @Query("nombre") nombre: String,
        @Query("telefono") telefono: String,
        @Query("fecha_nacimiento") fecha_nacimiento: String,
        @Query("biografia") biografia: String,
        @Query("foto") foto: String,
        @Query("categorias") categorias: IntArray
    ):Response<Usuario>

    @Headers("Accept: application/json")
    @POST("/proyecto/api/continue/register/{id}")
    suspend fun continuarRegistroBody(
        @Path("id") id: Int,
        @Body continuarRegistroBody: ContinuarRegistroBody
    ):Response<Usuario>

    @GET("/proyecto/api/events")
    suspend fun dameTodosEventos(): Response<List<Evento>>

    @GET("/proyecto/api/events/filter")
    suspend fun dameEventosFiltrados(
        @Query("categoria") categoria: String?,
        @Query("fecha_inicio") fecha_inicion: String?,
        @Query("fecha_fin") fecha_fin: String?,
//        @Query("tipo") tipo: String?,
        @Query("location") location: String?,
        @Query("latitude") latitude: Double?,
        @Query("longitude") longitude: Double?
    ): Response<List<Evento>>

    @GET("/proyecto/api/users/{id}/parati")
    suspend fun dameEventosParaMi(@Path("id") id: Int): Response<List<Evento>>

    @GET("/proyecto/api/users/{id}/pantallaseguidos")
    suspend fun dameEventosSeguidos(@Path("id") id: Int): Response<List<Evento>>

    @Headers("Accept: application/json")
    @POST("/proyecto/api/events")
    suspend fun crearEvento(
        @Query("titulo") titulo: String,
        @Query("fecha_hora_inicio") fecha_hora_inicio: String,
        @Query("fecha_hora_fin") fecha_hora_fin: String,
        @Query("descripcion") descripcion: String,
        @Query("foto") foto: String,
        @Query("tipo") tipo: Boolean,
        @Query("location") location: String,
        @Query("latitud") latitud: String,
        @Query("longitud") longitud: String,
        @Query("categoria_id") categoria_id: Int
    ): Response<Mensaje>

    @GET("/proyecto/api/users/{id}")
    suspend fun dameElUsuario(@Path("id") id: Int): Response<Usuario>

    @GET("/proyecto/api/categorias")
    suspend fun dameTodasCategorias(): Response<List<Categoria>>

    @GET("/proyecto/api/events/{id}")
    suspend fun dameElEvento(@Path("id") id: Int): Response<Evento>

    @GET("/proyecto/api/users/{id}/following")
    suspend fun dameMisSeguidos(@Path("id") id: Int): Response<List<Asistente>>
}