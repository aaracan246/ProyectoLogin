package com.example.proyectologin.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectologin.api.API
import com.example.proyectologin.api.ApiService
import com.example.proyectologin.api.LoginUsuarioDTO
import kotlinx.coroutines.launch

class LoginViewModel(): ViewModel() {

    private val _username = mutableStateOf("")
    val username: State<String> = _username
    private val _password = mutableStateOf("")
    val password: State<String> = _password

    private val _token = mutableStateOf("")
    val token: State<String> = _token

    fun usernameUpdate(value: String) { _username.value = value }
    fun passwordUpdate(value: String) { _password.value = value }

    suspend fun login(username: String, password: String): Boolean {
        val apiService = API.instance.create(ApiService::class.java)
        return try {
            val response = apiService.login(LoginUsuarioDTO(username, password))
            if (response.isSuccessful && response.body() != null) {
                _token.value = response.body()?.token ?: ""
                true
            } else {
                false
            }
        } catch (e: Exception) {
            println("Error logueando. ${e.message}.")
            false
        }
    }
}