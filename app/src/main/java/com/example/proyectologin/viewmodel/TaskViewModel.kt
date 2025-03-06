package com.example.proyectologin.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectologin.api.API
import com.example.proyectologin.api.ApiService
import com.example.proyectologin.api.Tarea
import kotlinx.coroutines.launch

class TaskViewModel: ViewModel() {
    private val _titulo = mutableStateOf("")
    val titulo: State<String> = _titulo
    private val _desc = mutableStateOf("")
    val desc: State<String> = _desc
    private val _status = mutableStateOf(false)
    val status: State<Boolean> = _status
    private val _usuario = mutableStateOf("")
    val usuario: State<String> = _usuario

    fun tituloUpdate(value: String) { _titulo.value = value }
    fun descUpdate(value: String) { _desc.value = value }
    fun statusUpdate(value: Boolean) { _status.value = value }
    fun usuarioUpdate(value: String) { _usuario.value = value }


    private val apiService = API.instance.create(ApiService::class.java)

    fun cargarTareas(token: String, onResult: (List<Tarea>?) -> Unit) {
        viewModelScope.launch {
            try {
                val response = apiService.getAllTareas(token)
                println(response)
                if (response.isSuccessful) {
                    val tareas = response.body()
                    onResult(tareas)
                    println("Tareas obtenidas: $tareas")
                } else {
                    // Imprimir el código de error y el cuerpo del error
                    val errorBody = response.errorBody()?.string()
                    println("Error en la respuesta: ${response.code()} - $errorBody")
                    onResult(null)
                }
            } catch (e: Exception) {
                // Imprimir el mensaje completo de la excepción
                println("Excepción: ${e.message}")
                onResult(null)
            }
        }
    }

}