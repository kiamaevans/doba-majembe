package com.example.doba_majembe.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.doba_majembe.ui.theme.screens.LoginScreen

import com.example.doba_majembe.ui.theme.screens.SignUpScreen





@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_LOGIN
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(ROUTE_LOGIN) { LoginScreen(navController) }
        composable (ROUTE_SIGNUP){ SignUpScreen(navController) }
    }
}



