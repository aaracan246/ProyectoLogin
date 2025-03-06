package com.example.proyectologin.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectologin.api.API
import com.example.proyectologin.api.ApiService
import com.example.proyectologin.api.Tarea
import com.example.proyectologin.viewmodel.LoginViewModel
import com.example.proyectologin.viewmodel.TaskViewModel

@Composable
fun FifthScreen(taskViewModel: TaskViewModel, loginViewModel: LoginViewModel) {
    AllTareas(taskViewModel, loginViewModel)
}


@Composable
fun AllTareas(taskViewModel: TaskViewModel, loginViewModel: LoginViewModel){


    var tareas by remember { mutableStateOf<List<Tarea>>(emptyList()) }



        LaunchedEffect(Unit) {
            val token = loginViewModel.token.value
            println("Token: $token")
            taskViewModel.cargarTareas(token){ result ->
                if (result != null){
                    tareas = result
                }
            }
        }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Lista de Tareas", style = MaterialTheme.typography.headlineMedium)

        LazyColumn {
            items(tareas) { tarea ->
                TareaItem(tarea)
            }
        }
    }
}

@Composable
fun TareaItem(tarea: Tarea) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(tarea.titulo, style = MaterialTheme.typography.titleLarge)
            Text(tarea.desc, style = MaterialTheme.typography.bodyMedium)
            Text(if (tarea.status) "Completada" else "Pendiente", style = MaterialTheme.typography.bodySmall)
        }
    }
}
    

