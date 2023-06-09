package com.example.qddapp.Retrofit

import com.example.qddapp.Modelos.Asistente
import com.example.qddapp.Modelos.Categoria
import com.example.qddapp.Modelos.ContinuarRegistroBody
import com.example.qddapp.Modelos.Evento
import com.example.qddapp.Modelos.EventoCrear
import com.example.qddapp.Modelos.EventoMiEvento
import com.example.qddapp.Modelos.Mensaje
import com.example.qddapp.Modelos.MensajeEvento
import com.example.qddapp.Modelos.Registro
import com.example.qddapp.Modelos.RegistroBody
import com.example.qddapp.Modelos.Relacion
import com.example.qddapp.Modelos.Token
import com.example.qddapp.Modelos.Usuario
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
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

    @Headers("Accept: application/json")
    @POST ("/proyecto/api/register")
    suspend fun registroBody(
        @Body registro: RegistroBody
    ): Response<Registro>

    @Headers("Accept: application/json")
    @POST("/proyecto/api/continue/register/{id}")
    suspend fun continuarRegistroBody(
        @Path("id") id: Int,
        @Body continuarRegistroBody: ContinuarRegistroBody
    ):Response<Mensaje>

    @DELETE("proyecto/api/users/{id}")
    suspend fun eliminarUsuario(@Path("id") id: Int) : Response<Mensaje>

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
    @POST("proyecto/api/validate/phone")
    suspend fun hayTelefono(@Body telefono: String): Response<Mensaje>

    @Headers("Accept: application/json")
    @POST("/proyecto/api/events")
    suspend fun crearEventoBody(@Body evento: EventoCrear): Response<MensajeEvento>

    @GET("/proyecto/api/users/{id}")
    suspend fun dameElUsuario(@Path("id") id: Int): Response<Usuario>

    @GET("/proyecto/api/categorias")
    suspend fun dameTodasCategorias(): Response<List<Categoria>>

    @GET("/proyecto/api/events/{id}")
    suspend fun dameElEvento(@Path("id") id: Int): Response<Evento>

    @DELETE("/proyecto/api/events/{id}")
    suspend fun borrarEvento(@Path("id") id: Int): Response<Mensaje>

    @GET("/proyecto/api/users/{id}/following")
    suspend fun dameMisSeguidos(@Path("id") id: Int): Response<List<Asistente>>

    @GET("/proyecto/api/users/{id}/events")
    suspend fun dameMisEventos(@Path("id") id: Int): Response<List<EventoMiEvento>>

    @GET("/proyecto/api/users/{id}/historial")
    suspend fun dameMiHistorial(@Path("id") id: Int): Response<List<Evento>>

    @Headers("Accept: application/json")
    @POST("proyecto/api/events/{id}/join")
    suspend fun solicitarUnirseEvento(@Path("id") id: Int): Response<Mensaje>

    @GET("proyecto/api/events/{id}/relationUser")
    suspend fun miRelacionConEvento(@Path("id") id: Int): Response<Relacion>

    @Headers("Accept: application/json")
    @POST("proyecto/api/users/{id}/follow")
    suspend fun seguirUsuario(@Path("id") id: Int): Response<Mensaje>

    @Headers("Accept: application/json")
    @POST("proyecto/api/users/{id}/unfollow")
    suspend fun dejarSeguirUsuario(@Path("id") id: Int): Response<Mensaje>

    @Headers("Accept: application/json")
    @POST("proyecto/api/logout")
    suspend fun cerrarSesion(): Response<Mensaje>

    @Headers("Accept: application/json")
    @POST("proyecto/api/users/{id}/verifyFollowing")
    suspend fun teSigo(@Path("id") id: Int): Response<Mensaje>
}