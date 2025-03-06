package com.example.proyectologin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.proyectologin.api.ApiService
import com.example.proyectologin.navigation.AppNavigation
import com.example.proyectologin.ui.theme.ProyectoLoginTheme
import com.example.proyectologin.viewmodel.AppViewModel
import com.example.proyectologin.viewmodel.LoginViewModel
import com.example.proyectologin.viewmodel.RegisterViewModel
import com.example.proyectologin.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoLoginTheme {

                val navControlador = rememberNavController()
                val appViewModel = AppViewModel()
                val registerViewModel = RegisterViewModel()
                val loginViewModel = LoginViewModel()
                val taskViewModel = TaskViewModel()

                AppNavigation(navControlador, appViewModel, registerViewModel, loginViewModel, taskViewModel)

            }
        }
    }
}

