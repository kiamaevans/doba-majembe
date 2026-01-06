package com.example.doba_majembe.navigation

<<<<<<< feature/signup-UI
<<<<<<< Updated upstream
=======
=======
>>>>>>> developer
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.doba_majembe.ui.theme.screens.LoginScreen
<<<<<<< feature/signup-UI
import com.example.doba_majembe.ui.theme.screens.SignUpScreen
=======

>>>>>>> developer


@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_LOGIN
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
<<<<<<< feature/signup-UI
        composable(ROUTE_LOGIN) { LoginScreen(navController) }
        composable (ROUTE_SIGNUP){ SignUpScreen(navController) }
    }
}
>>>>>>> Stashed changes
=======
        composable(ROUTE_LOGIN) {
            LoginScreen(navController)
        }
    }
}
>>>>>>> developer
