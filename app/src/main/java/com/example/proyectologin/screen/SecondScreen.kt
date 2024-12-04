package com.example.proyectologin.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectologin.R
import com.example.proyectologin.navigation.AppScreen

@Composable
fun SecondScreen(navControlador: NavController){
    SecondBody(navControlador)
}

@Composable
fun SecondBody(navControlador: NavController){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.fondo)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            colors = CardDefaults.cardColors(containerColor = colorResource(R.color.fondo))
        ) {
            Header()

            GodotDivider()

            Spacer(modifier = Modifier.padding(top = 16.dp))

            Button(
                onClick = {  },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
                    .border(1.dp, Color.White, RoundedCornerShape(8.dp)),
                shape = RoundedCornerShape(8.dp), colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                )
            ) {
                Text("Accede con Github")
            }

            Button(
                onClick = {  },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
                    .border(1.dp, Color.White, RoundedCornerShape(8.dp)),
                shape = RoundedCornerShape(8.dp), colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                )
            ) {
                Text("Accede con Twitter")
            }

            Button(
                onClick = {  },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
                    .border(1.dp, Color.White, RoundedCornerShape(8.dp)),
                shape = RoundedCornerShape(8.dp), colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                )
            ) {
                Text("Accede con Google")
            }

            Button(
                onClick = {  },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
                    .border(1.dp, Color.White, RoundedCornerShape(8.dp)),
                shape = RoundedCornerShape(8.dp), colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                )
            ) {
                Text("Accede con auth0")
            }

            Spacer(modifier = Modifier.padding(top = 16.dp))

            GodotDivider()

            Button(modifier = Modifier.fillMaxWidth().padding(top = 12.dp), onClick = { navControlador.popBackStack() }) {
                Text("Volver a la pantalla anterior")
            }

        }
    }
}