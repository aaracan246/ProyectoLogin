package com.example.proyectologin.api

data class UsuarioRegisterDTO(
    val username: String,
    val password: String,
    val passwordRepeat: String,
    val email: String,
    val rol: String,
    val direccion: Direccion
)
