package com.example.proyectologin.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AppViewModel: ViewModel() {

    // Aquí iban todos los estados y luego inyectamos el ViewModel en las distintas pantallas para hacer uso de los estados elevados


    private val _username = MutableStateFlow("") // MutableStateFlow -> Maneja estados mediante .value / Permite el uso de .collectAsState y se recompone inmediatamente cuando su valor cambia
    val username: StateFlow<String> = _username

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password


    fun usernameUpdate(newUser: String){
        _username.update { newUser }
    }

    fun passwordUpdate(newPassword: String){
        _password.update { newPassword }
    }

    private val _checkRememberPass = MutableStateFlow(false)
    val checkRememberPass: StateFlow<Boolean> = _checkRememberPass

    fun checkRememberPassUpdate(isChecked: Boolean){
        _checkRememberPass.value = isChecked
    }
}