package za.co.todoapp.common.services.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import org.koin.compose.koinInject
import za.co.todoapp.presentation.home.HomeScreen
import za.co.todoapp.presentation.menu.MenuScreen
import za.co.todoapp.presentation.menu.MenuScreenViewModel
import za.co.todoapp.presentation.task.TaskScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val navigator = koinInject<Navigator>()
    ObserveAsEvents(flow = navigator.navigationActions) { action ->
        when(action) {
            is NavigationAction.Navigate -> {
                navController.navigate(action.destination) {
                    action.navOptions(this)
                }
            }
            is NavigationAction.NavigateUp -> {
                navController.navigateUp()
            }
        }
    }
    NavHost(navController = navController, startDestination = navigator.startDestination) {
        navigation<Destination.HomeGraph>(
            startDestination = Destination.HomeScreen
        ) {
            composable<Destination.HomeScreen> {
                HomeScreen()
            }

            composable<Destination.MenuScreen> {
                val menuScreenViewModel = koinInject<MenuScreenViewModel>()
                MenuScreen(isDarkMode = menuScreenViewModel.isDarkModeMutableState.value) {}
            }

            composable<Destination.TaskScreen> {
                TaskScreen()
            }
        }
    }
}