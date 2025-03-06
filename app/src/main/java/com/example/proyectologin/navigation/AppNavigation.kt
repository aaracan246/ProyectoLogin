package com.example.proyectologin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.proyectologin.screen.FifthScreen
import com.example.proyectologin.screen.FirstScreen
import com.example.proyectologin.screen.FourthScreen
import com.example.proyectologin.screen.SecondScreen
import com.example.proyectologin.screen.SixthScreen
import com.example.proyectologin.screen.ThirdScreen
import com.example.proyectologin.viewmodel.AppViewModel
import com.example.proyectologin.viewmodel.LoginViewModel
import com.example.proyectologin.viewmodel.RegisterViewModel
import com.example.proyectologin.viewmodel.TaskViewModel

@Composable
fun AppNavigation(navController: NavHostController, appViewModel: AppViewModel, registerViewModel: RegisterViewModel, loginViewModel: LoginViewModel, taskViewModel: TaskViewModel){


    NavHost(navController = navController, startDestination = AppScreen.FirstScreen.route){

        composable(AppScreen.FirstScreen.route){ FirstScreen(navController, appViewModel, loginViewModel) }

        composable(AppScreen.SecondScreen.route){ SecondScreen(navController)}

        composable(AppScreen.ThirdScreen.route) { ThirdScreen(navController) }

        composable(AppScreen.FourthScreen.route) { FourthScreen(navController, registerViewModel)}

        composable(AppScreen.FifthScreen.route) { FifthScreen(taskViewModel, loginViewModel) }

        composable(AppScreen.SixthScreen.route) { SixthScreen(navController, loginViewModel) }

    }
}
