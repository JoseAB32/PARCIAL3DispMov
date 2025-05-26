package com.example.parcial3.Navigation

import androidx.activity.OnBackPressedCallback
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.parcial3.FormEnvioSim.FormEnvioSimScreen
import com.example.parcial3.Navigation.Screens.PlanesScreen
import com.example.parcial3.planes.PlanesScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.PlanesScreen.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        composable(Screens.PlanesScreen.route) {
            PlanesScreen(
                onSuccess = {
                    navController.navigate(Screens.FormEnvioSimScreen.route)
                }
            )
        }

        composable(Screens.FormEnvioSimScreen.route) {
            FormEnvioSimScreen(
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }

    }
}