package com.example.proyectologin.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectologin.R
import com.example.proyectologin.navigation.AppScreen
import com.example.proyectologin.viewmodel.AppViewModel

@Composable
fun FirstScreen(navControlador: NavController, appViewModel: AppViewModel){
    LoginScreen(navControlador, appViewModel)
}

@Composable
fun LoginScreen(navControlador: NavController, appViewModel: AppViewModel){
    val username by appViewModel.username.collectAsState()
    val password by appViewModel.password.collectAsState()
    val passwdRemember by appViewModel.checkRememberPass.collectAsState()



    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 32.dp)
                        .background(color = colorResource(R.color.fondo))) {


        Header()

        Row(verticalAlignment = Alignment.CenterVertically) {
            HorizontalDivider(modifier = Modifier.weight(1f).padding(end = 8.dp))
            Image(painter = painterResource(R.drawable.godoticon), contentDescription = "Decoración con icono", modifier = Modifier.size(32.dp), alignment = Alignment.Center)
            HorizontalDivider(modifier = Modifier.weight(1f).padding(start = 8.dp))
        }
            // login start

        Column(modifier = Modifier.padding(6.dp)) {
            OutlinedTextField(
                value = username,
                onValueChange = { appViewModel.usernameUpdate(it) },
                label = { Text("Usuario / Email") },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    colorResource(R.color.white)
                )
            )

            Spacer(modifier = Modifier.padding(8.dp))



            OutlinedTextField(
                value = password,
                onValueChange = { appViewModel.passwordUpdate(it) },
                label = { Text("Contraseña") },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    colorResource(R.color.white)
                )

            )

            Row {
                CheckboxFun(
                    text = "¿Recordarme?", passwdRemember
                ) { appViewModel.checkRememberPassUpdate(it) }

                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Text("¿Olvidaste tu contraseña?", modifier = Modifier
                        .clickable { }
                        .padding(top = 12.dp),
                        color = colorResource(R.color.white),
                        textDecoration = TextDecoration.Underline)
                }
            }
            Button(
                onClick = { navControlador.navigate(route = AppScreen.SecondScreen.route) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Conectarse", fontWeight = FontWeight.Bold)
            }

        }


        // login end

        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter) {
            Footer()
        }
    }

}

@Composable
fun Header(){

    Row(
        modifier = Modifier.padding(26.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center) {

            Image(painter = painterResource(R.drawable.godoticon), contentDescription = "Imagen del icono de Godot")
            Text("Godot Community", modifier = Modifier.padding(start = 12.dp), color = colorResource(R.color.white))
    }
}

@Composable
fun Footer(){

    Column(modifier = Modifier
            .padding(26.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row {

            Text("© 2023", color = colorResource(R.color.white), modifier = Modifier.padding(end = 8.dp))
            Text("Godot Community", modifier = Modifier.clickable {  }, color = colorResource(R.color.white))
        }

        Spacer(modifier = Modifier.padding(16.dp))

        Row {
            Text("Privacy", modifier =  Modifier.clickable {  }, color = colorResource(R.color.white))
            Text("-", color = colorResource(R.color.white))
            Text("Terms", modifier = Modifier.clickable {  }, color = colorResource(R.color.white))
            Text("-", color = colorResource(R.color.white))
            Text("Contact", modifier = Modifier.clickable {  }, color = colorResource(R.color.white))
        }
    }
}

@Composable
fun CheckboxFun(text: String, isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {


    Row(
        verticalAlignment = Alignment.CenterVertically){
        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                uncheckedColor = Color.White
            )
        )
        Text(text = text, modifier = Modifier.padding(start = 6.dp), fontWeight = FontWeight.Bold, color = colorResource(R.color.white))
    }
}