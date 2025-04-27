package za.co.todoapp.common.services.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import za.co.todoapp.presentation.home.HomeScreen
import za.co.todoapp.presentation.home.HomeScreenViewModel
import za.co.todoapp.presentation.menu.MenuScreen
import za.co.todoapp.presentation.menu.MenuScreenViewModel
import za.co.todoapp.presentation.task.TaskScreen
import za.co.todoapp.presentation.task.TaskScreenViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val navigator = koinInject<Navigator>()
    ObserveAsEvents(flow = navigator.navigationActions) { action ->
        when (action) {
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
                val homeScreenViewModel = koinInject<HomeScreenViewModel>()
                HomeScreen(
                    taskState = homeScreenViewModel.taskState.value,
                    onTaskTabClicked = { isComplete ->
                        homeScreenViewModel.getAllTaskByCompleteStatus(isComplete = isComplete)
                    },
                    onNavigateToTaskScreenClicked = {
                        homeScreenViewModel.navigateToTaskScreen()
                    }) {
                    homeScreenViewModel.navigateToMenuScreen()
                }
            }

            composable<Destination.MenuScreen> {
                val menuScreenViewModel = koinInject<MenuScreenViewModel>()
                MenuScreen(
                    isDarkMode = menuScreenViewModel.isDarkModeMutableState.value,
                    onNavigateUp = {
                        menuScreenViewModel.navigateUp()
                    }
                ) {}
            }

            composable<Destination.TaskScreen> {
                val taskScreenViewModel = koinInject<TaskScreenViewModel>()
                TaskScreen(
                    taskName = taskScreenViewModel.taskName.value,
                    taskDescription = taskScreenViewModel.taskDescription.value,
                    onNavigateUp = {
                        taskScreenViewModel.navigateUp()
                    },
                    onTaskNameValueChanged = { value ->
                        taskScreenViewModel.taskName.value = value
                    },
                    onTaskDescriptionValueChanged = { value ->
                        taskScreenViewModel.taskDescription.value = value
                    }
                ) {
                    //TODO: Save the task
                }
            }
        }
    }
}