package com.example.proyectologin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyectologin.screen.FirstScreen
import com.example.proyectologin.screen.SecondScreen

@Composable
fun AppNavigation(){

    val navControlador = rememberNavController()
    NavHost(navController = navControlador, startDestination = AppScreen.FirstScreen.route){

        composable(AppScreen.FirstScreen.route){
            FirstScreen(navControlador)
        }

        composable(AppScreen.SecondScreen.route + "/" + "{name}" + "/" + "{age}",
            arguments = listOf(navArgument(name = "name"){ type = NavType.StringType }, navArgument(name = "age") { type = NavType.IntType})){ // Añadimos más argumentos posibles para pasar también la edad
            SecondScreen(navControlador, it.arguments?.getString("name"), it.arguments?.getInt("age"))
        }
    }
}