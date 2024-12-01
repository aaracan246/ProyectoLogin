package com.example.proyectologin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyectologin.screen.FirstScreen
import com.example.proyectologin.screen.SecondScreen
import com.example.proyectologin.viewmodel.AppViewModel

@Composable
fun AppNavigation(navController: NavHostController, appViewModel: AppViewModel){


    NavHost(navController = navController, startDestination = AppScreen.FirstScreen.route){

        composable(AppScreen.FirstScreen.route){ FirstScreen(navController, appViewModel) }

        composable(AppScreen.SecondScreen.route + "/" + "{user}" + "/" + "{password}",
            arguments = listOf(navArgument(name = "user"){ type = NavType.StringType }, navArgument(name = "password") { type = NavType.StringType})){ // Añadimos más argumentos posibles para pasar también la edad
            SecondScreen(navController, it.arguments?.getString("user"), it.arguments?.getInt("password"))
        }
    }
}