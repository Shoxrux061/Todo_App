package uz.test.todo.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uz.test.todo.core.constants.NavRoutes
import uz.test.todo.presentation.screens.home_screen.HomeScreen
import uz.test.todo.presentation.screens.home_screen.HomeScreenViewModel

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    val homeScreenViewModel = hiltViewModel<HomeScreenViewModel>()

    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = NavRoutes.HOME_SCREEN
    ) {

        composable(NavRoutes.HOME_SCREEN) {
            HomeScreen(homeScreenViewModel, navController)
        }

        composable(NavRoutes.DETAIL_SCREEN) {

        }

    }

}