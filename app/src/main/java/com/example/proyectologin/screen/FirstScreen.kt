package com.example.proyectologin.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectologin.R
import com.example.proyectologin.navigation.AppScreen
import com.example.proyectologin.viewmodel.AppViewModel
import com.example.proyectologin.viewmodel.LoginViewModel
import kotlinx.coroutines.launch
import kotlin.math.log

@Composable
fun FirstScreen(navControlador: NavController, appViewModel: AppViewModel, loginViewModel: LoginViewModel){
    LoginScreen(navControlador, appViewModel, loginViewModel)
}

@Composable
fun LoginScreen(navControlador: NavController, appViewModel: AppViewModel, loginViewModel: LoginViewModel){

    // Credenciales para comprobar el login:
    val validUsername = "Morri"
    val validPassword = "1234"


    // Estados para el ViewModel:
    val username by appViewModel.username.collectAsState()
    val password by appViewModel.password.collectAsState()
    val isChecked by appViewModel.isChecked.collectAsState()
    val isError by appViewModel.isError.collectAsState()
    val token = loginViewModel.token.value

    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 32.dp)
        .background(color = colorResource(R.color.fondo))) {


        Header()

        GodotDivider()

        if (isError){
            ErrorAuthentication()
        }

        // login start
        Column(modifier = Modifier.padding(6.dp)) {
            OutlinedTextField(
                value = username,
                onValueChange = { appViewModel.usernameUpdate(it)
                                    loginViewModel.usernameUpdate(it)
                                },
                label = { Text("Usuario / Email", modifier = Modifier.background(color = Color.Transparent)) },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = colorResource(R.color.white),
                    focusedLabelColor = Color.White,
                    unfocusedContainerColor = colorResource(R.color.white)
                )
            )

            Spacer(modifier = Modifier.padding(8.dp))



            OutlinedTextField(
                value = password,
                onValueChange = { appViewModel.passwordUpdate(it) },
                label = { Text("Contraseña",  modifier = Modifier.background(color = Color.Transparent)) },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor =  colorResource(R.color.white),
                    focusedLabelColor = Color.White,
                    unfocusedContainerColor = colorResource(R.color.white)



                ),
                visualTransformation = PasswordVisualTransformation()

            )

            Row {
                CheckboxFun(
                    text = "¿Recordarme?",
                    isChecked = isChecked,
                ) { appViewModel.checkRememberMe() }

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
                onClick = {
                    coroutineScope.launch {
                        loginViewModel.login(username, password)

                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Conectarse", fontWeight = FontWeight.Bold)
            }

        }



        }

        Spacer(Modifier.padding(top = 16.dp))

        HorizontalDivider(Modifier.padding(8.dp))

        Text("¿Aún no tienes cuenta?", color = Color.White, modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 12.dp))

        Button(
            onClick = { navControlador.navigate(AppScreen.FourthScreen.route) },
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.White, RoundedCornerShape(8.dp)),
            shape = RoundedCornerShape(8.dp), colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            )
        ) {
             Text("Registrarse")
        }

        Spacer(Modifier.padding(top = 16.dp))

        HorizontalDivider(Modifier.padding(8.dp))

        // acceso alternativo
        Column(modifier = Modifier.fillMaxWidth()) {

            Button(
                onClick = { navControlador.navigate(AppScreen.SecondScreen.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
                    .border(1.dp, Color.White, RoundedCornerShape(8.dp)),
                shape = RoundedCornerShape(8.dp), colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                )
            ) {
                Text("Acceso alternativo")
            }
        // acceso alternativo

        }
        // login end

        Spacer(Modifier.padding(top = 16.dp))

        HorizontalDivider(Modifier.padding(8.dp))

        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter) {
            Footer()
        }

    LaunchedEffect(token) {
        if (token.isNotEmpty()) {
            navControlador.navigate(AppScreen.ThirdScreen.route)
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

@Composable
fun GodotDivider(){
    Row(verticalAlignment = Alignment.CenterVertically) {
        HorizontalDivider(modifier = Modifier
            .weight(1f)
            .padding(end = 8.dp))

        Image(
            painter = painterResource(R.drawable.godoticon),
            contentDescription = "Decoración con icono",
            modifier = Modifier
                .size(32.dp),
            alignment = Alignment.Center)

        HorizontalDivider(modifier = Modifier
            .weight(1f)
            .padding(start = 8.dp))
    }
}

@Composable
fun ErrorAuthentication(){
    Row {
        Text("Los datos de autenticación son erróneos.", color = Color.Red, modifier = Modifier.padding(8.dp))
    }
}