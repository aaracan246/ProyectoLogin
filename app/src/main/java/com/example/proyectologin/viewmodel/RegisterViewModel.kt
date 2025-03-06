package com.example.proyectologin.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectologin.api.API
import com.example.proyectologin.api.ApiService
import com.example.proyectologin.api.Direccion
import com.example.proyectologin.api.UsuarioRegisterDTO
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    private val _username = mutableStateOf("")
    val username: State<String> = _username
    private val _password = mutableStateOf("")
    val password: State<String> = _password
    private val _passwordRepeat = mutableStateOf("")
    val passwordRepeat: State<String> = _passwordRepeat
    private val _email = mutableStateOf("")
    val email: State<String> = _email
    private val _rol = mutableStateOf("")
    val rol: State<String> = _rol
    private val _municipio = mutableStateOf("")
    val municipio: State<String> = _municipio
    private val _provincia = mutableStateOf("")
    val provincia: State<String> = _provincia

    fun usernameUpdate(value: String) { _username.value = value }
    fun passwordUpdate(value: String) { _password.value = value }
    fun passwordRepeatUpdate(value: String) { _passwordRepeat.value = value }
    fun emailUpdate(value: String) { _email.value = value }
    fun rolUpdate(value: String) { _rol.value = value }
    fun municipioUpdate(value: String) { _municipio.value = value }
    fun provinciaUpdate(value: String) { _provincia.value = value }

    fun registerUser() {
        val user = UsuarioRegisterDTO(
            username.value, password.value, passwordRepeat.value, email.value, rol.value, Direccion(municipio.value, provincia.value)
        )
        viewModelScope.launch {
            val apiService = API.instance.create(ApiService::class.java)
            try {
                val response = apiService.register(user)
                if (response.isSuccessful) {
                    println("Registro exitoso")
                } else {
                    println("Error en el registro")
                }
            } catch (e: Exception) {
                println("Error en la conexión: ${e.message}")
            }
        }
    }
}