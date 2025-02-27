package com.example.proyectologin.viewmodel

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

open class AppViewModel: ViewModel() {

    // Aquí iban todos los estados y luego inyectamos el ViewModel en las distintas pantallas para hacer uso de los estados elevados


    private val _username = MutableStateFlow("") // MutableStateFlow -> Maneja estados mediante .value / Permite el uso de .collectAsState y se recompone inmediatamente cuando su valor cambia
    open val username: StateFlow<String> = _username

    private val _password = MutableStateFlow("")
    open val password: StateFlow<String> = _password

    private val _isChecked = MutableStateFlow(false)
    open val isChecked: StateFlow<Boolean> = _isChecked

    private val _isError = MutableStateFlow(false)
     open val isError: StateFlow<Boolean> = _isError

    fun usernameUpdate(newUser: String){
        _username.update { newUser }
    }

    fun passwordUpdate(newPassword: String){
        _password.update { newPassword }
    }

    private val _checkRememberUsername = MutableStateFlow(false)
    val checkRememberUsername: StateFlow<Boolean> = _checkRememberUsername

    private val _checkRememberPass = MutableStateFlow(false)
    val checkRememberPass: StateFlow<Boolean> = _checkRememberPass

    fun checkRememberMe(){
       _isChecked.value = !_isChecked.value
    }

    fun changeErrorValue(state: Boolean){
        _isError.value = state
    }


}