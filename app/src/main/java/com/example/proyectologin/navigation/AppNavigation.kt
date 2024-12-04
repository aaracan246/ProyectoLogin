package com.example.proyectologin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.proyectologin.screen.FirstScreen
import com.example.proyectologin.screen.SecondScreen
import com.example.proyectologin.screen.ThirdScreen
import com.example.proyectologin.viewmodel.AppViewModel

@Composable
fun AppNavigation(navController: NavHostController, appViewModel: AppViewModel){


    NavHost(navController = navController, startDestination = AppScreen.FirstScreen.route){

        composable(AppScreen.FirstScreen.route){ FirstScreen(navController, appViewModel) }

        composable(AppScreen.SecondScreen.route){ SecondScreen(navController)}

        composable(AppScreen.ThirdScreen.route) { ThirdScreen(navController) }

    }
}
