package com.example.proyectologin.api

data class Tarea(
    val _id: String?,
    val titulo: String,
    val desc: String,
    val status: Boolean,
    val usuario: String
)
