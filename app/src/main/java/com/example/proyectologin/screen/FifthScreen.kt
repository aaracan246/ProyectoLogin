package com.example.proyectologin.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.proyectologin.api.API
import com.example.proyectologin.api.ApiService
import com.example.proyectologin.api.Tarea
import com.example.proyectologin.viewmodel.LoginViewModel
import com.example.proyectologin.viewmodel.TaskViewModel
import kotlinx.coroutines.launch

@Composable
fun FifthScreen(taskViewModel: TaskViewModel, loginViewModel: LoginViewModel) {
    AllTareas(taskViewModel, loginViewModel)
}

@Composable
fun AllTareas(taskViewModel: TaskViewModel, loginViewModel: LoginViewModel) {

    val apiService = API.instance.create(ApiService::class.java)
    val token = loginViewModel.token.value
    var tareas by remember { mutableStateOf<List<Tarea>>(emptyList()) }

    LaunchedEffect(Unit) {
        taskViewModel.cargarTareas(token) { result ->
            if (result != null) {
                tareas = result
            }
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Lista de Tareas", style = MaterialTheme.typography.headlineMedium)

        LazyColumn {
            items(tareas) { tarea ->
                TareaItem(
                    token,
                    tarea,
                    onTaskUpdated = { completedTask ->
                    tareas = tareas.map { if (it._id == completedTask._id) completedTask else it }
                }, onTaskDeleted = { deletedTask ->
                    tareas = tareas.filter { it._id != deletedTask._id }
                })
            }
        }
    }
}

@Composable
fun TareaItem(
    token: String,
    tarea: Tarea,
    onTaskUpdated: (Tarea) -> Unit,
    onTaskDeleted: (Tarea) -> Unit
    ) {

    val coroutineScope = rememberCoroutineScope()
    val apiService = API.instance.create(ApiService::class.java)
    var isChecked by remember { mutableStateOf(tarea.status == true) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { checked ->
                    isChecked = checked
                    coroutineScope.launch {
                        try {
                            val response = tarea._id?.let { apiService.completeTarea(token, it) }
                            if (response != null) {
                                if (response.isSuccessful) {
                                    response.body()?.let { completedTask ->
                                        onTaskUpdated(completedTask)
                                    }
                                }
                            }
                        } catch (e: Exception) {
                            println("No se pudo completar la tarea. ${e.message}")
                        }
                    }
                }
            )

            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(
                    text = tarea.titulo,
                    style = MaterialTheme.typography.titleLarge,
                    textDecoration = if (isChecked) TextDecoration.LineThrough else TextDecoration.None
                )
                Text(
                    text = tarea.desc,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            IconButton(onClick = {
                coroutineScope.launch {
                    try {
                        val response = tarea._id?.let { apiService.deleteTarea(token, it) }
                        if (response != null && response.isSuccessful) {
                            onTaskDeleted(tarea)
                        }
                    } catch (e: Exception) {
                        println("No se pudo eliminar la tarea. ${e.message}")
                    }
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Eliminar tarea"
                )
            }
        }
    }
}

    

