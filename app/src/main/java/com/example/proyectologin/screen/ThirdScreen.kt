package com.example.proyectologin.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.proyectologin.navigation.AppScreen


@Composable
fun ThirdScreen(navController: NavController){
    ThirdBody(navController)
}

@Composable
fun ThirdBody(navController: NavController) {

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Button(
            onClick = { navController.navigate(route = AppScreen.SixthScreen.route) }
        ) { Text("Insertar tarea") }

        Button(
            onClick = { navController.navigate(route = AppScreen.FifthScreen.route) }
        ) { Text("Completar tarea") }

        Button(
            onClick = { navController.navigate(route = AppScreen.FifthScreen.route) }
        ) { Text("Ver todas las tareas") }

        Button(
            onClick = { navController.navigate(route = AppScreen.FifthScreen.route) }
        ) { Text("Borrar una tarea") }

        Button(onClick = { navController.popBackStack() }) {
            Text("Volver a la pantalla anterior")
        }
    }
}