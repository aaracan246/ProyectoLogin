package com.example.proyectologin.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {

    @POST("/usuarios/login")
    suspend fun login(
        @Body requestBody: LoginUsuarioDTO
    ): Response<TokenCorrectLogin>

    @POST("/usuarios/register")
    suspend fun register(
        @Body requestBody: UsuarioRegisterDTO
    ): Response<UsuarioDTO>

    @POST("/tareas/insert_tarea")
    suspend fun insertTarea(
        @Body requestBody: Tarea
    ): Response<Tarea>

    @GET("/tareas/allTareas")
    suspend fun getAllTareas(@Header("Authorization") token: String): Response<List<Tarea>>

    @PUT("/tareas/complete_tarea/{id}")
    suspend fun completeTarea(): Response<Tarea>

    @DELETE("/tareas/delete_tarea/{id}")
    suspend fun deleteTarea(): Response<String>
}