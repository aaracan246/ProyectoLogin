package com.example.proyectologin.screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectologin.R
import com.example.proyectologin.api.API
import com.example.proyectologin.api.ApiService
import com.example.proyectologin.api.Tarea
import com.example.proyectologin.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

@Composable
fun SixthScreen(navController: NavController, loginViewModel: LoginViewModel) {
    TaskInsert(navController, loginViewModel)
}

@Composable
fun TaskInsert(navController: NavController, loginViewModel: LoginViewModel) {
    val tareaTitle = remember { mutableStateOf("") }
    val tareaDescription = remember { mutableStateOf("") }
    val isLoading = remember { mutableStateOf(false) }
    val token = loginViewModel.token.value
    val username = loginViewModel.username.value

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(color = colorResource(R.color.fondo))
    ) {
        Text("Inserta una nueva tarea", fontWeight = FontWeight.Bold, color = Color.White, fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de título de la tarea
        OutlinedTextField(
            value = tareaTitle.value,
            onValueChange = { tareaTitle.value = it },
            label = { Text("Título de la tarea") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = colorResource(R.color.white),
                focusedLabelColor = Color.White,
                unfocusedContainerColor = colorResource(R.color.white)
            )
        )

        // Campo de descripción de la tarea
        OutlinedTextField(
            value = tareaDescription.value,
            onValueChange = { tareaDescription.value = it },
            label = { Text("Descripción de la tarea") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = colorResource(R.color.white),
                focusedLabelColor = Color.White,
                unfocusedContainerColor = colorResource(R.color.white)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de "Insertar tarea"
        Button(
            onClick = {
                if (tareaTitle.value.isNotEmpty() && tareaDescription.value.isNotEmpty() && token.isNotEmpty() && username.isNotEmpty()) {
                    isLoading.value = true
                    coroutineScope.launch {
                        insertTarea(tareaTitle.value, tareaDescription.value, username, token, context)
                        isLoading.value = false
                    }
                } else {
                    Toast.makeText(context, "Por favor ingresa todos los campos y asegúrate de estar logueado", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            enabled = !isLoading.value
        ) {
            Text("Insertar")

            if (isLoading.value) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

private suspend fun insertTarea(title: String, description: String, username: String, token: String, context: Context) {
    val apiService = API.instance.create(ApiService::class.java)


    val tarea = Tarea(
        _id = null,
        titulo = title,
        desc = description,
        status = false,
        usuario = username
    )

    try {
        val response = apiService.insertTarea(token, tarea)

        if (response.isSuccessful) {
            Toast.makeText(context, "Tarea insertada con éxito", Toast.LENGTH_SHORT).show()
        } else {
            val errorBody = response.errorBody()?.string()
            Toast.makeText(context, "Error al insertar la tarea", Toast.LENGTH_SHORT).show()
            Log.e("InsertTarea", "Error: $errorBody")
        }
    }
    catch (e: Exception){
        Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        Log.e("InsertTarea", "Excepción: ${e.message}", e)
    }
}
