package com.example.proyectologin.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import org.w3c.dom.Text

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.viewModelScope
import com.example.proyectologin.api.API
import com.example.proyectologin.api.ApiService
import com.example.proyectologin.api.Direccion
import com.example.proyectologin.api.UsuarioRegisterDTO
import com.example.proyectologin.viewmodel.RegisterViewModel
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Composable
fun FourthScreen(navController: NavController, viewModel: RegisterViewModel){
    RegisterScreen(viewModel)
}

@Composable
fun RegisterScreen(viewModel: RegisterViewModel) {
    val username by viewModel.username
    val password by viewModel.password
    val passwordRepeat by viewModel.passwordRepeat
    val email by viewModel.email
    val rol by viewModel.rol
    val municipio by viewModel.municipio
    val provincia by viewModel.provincia

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(value = username, onValueChange = { viewModel.usernameUpdate(it) }, label = { Text("Username") })
        TextField(value = email, onValueChange = { viewModel.emailUpdate(it) }, label = { Text("Email") })
        TextField(
            value = password,
            onValueChange = { viewModel.passwordUpdate(it) },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        TextField(
            value = passwordRepeat,
            onValueChange = { viewModel.passwordRepeatUpdate(it) },
            label = { Text("Repeat Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        TextField(value = rol, onValueChange = { viewModel.rolUpdate(it) }, label = { Text("Rol") })
        TextField(value = municipio, onValueChange = { viewModel.municipioUpdate(it) }, label = { Text("Municipio") })
        TextField(value = provincia, onValueChange = { viewModel.provinciaUpdate(it) }, label = { Text("Provincia") })

        Button(onClick = { viewModel.registerUser() }) {
            Text("Register")
        }
    }
}




