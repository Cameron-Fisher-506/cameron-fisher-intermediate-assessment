package za.co.todoapp.common.services.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import org.koin.compose.koinInject
import za.co.todoapp.presentation.home.HomeScreen
import za.co.todoapp.presentation.home.HomeScreenViewModel
import za.co.todoapp.presentation.menu.MenuScreen
import za.co.todoapp.presentation.menu.MenuScreenViewModel
import za.co.todoapp.presentation.task.TaskScreen
import za.co.todoapp.presentation.task.TaskScreenViewModel

@Composable
fun Navigation(
    onCheckChangedDarkMode: (isDarkMode: Boolean) -> Unit
) {
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
                    taskState = homeScreenViewModel.taskState,
                    currentWeatherState = homeScreenViewModel.currentWeatherState,
                    tabItemList = homeScreenViewModel.getTabItemList(),
                    snackbarHostState = homeScreenViewModel.snackbarHostState,
                    onCreate = {
                        onCheckChangedDarkMode(homeScreenViewModel.isDarkMode())
                        homeScreenViewModel.getAllTaskByCompleteStatus(false)
                    },
                    onGetDeviceLocation = { isLocationPermissionGranted ->
                        homeScreenViewModel.getDeviceLocation(isLocationPermissionGranted) { latitude, longitude ->
                            homeScreenViewModel.fetchTodayWeatherForecast(latitude, longitude)
                        }
                    },
                    onDeleteTask = { task ->
                        homeScreenViewModel.deleteTask(task)
                    },
                    onCompleteTask = { task ->
                        task.isComplete = true
                        homeScreenViewModel.updateTaskCompleteStatus(task)
                    },
                    onUndoCompletedTask = { task ->
                        task.isComplete = false
                        homeScreenViewModel.updateTaskCompleteStatus(task)
                    },
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
                    isDarkMode = menuScreenViewModel.isDarkModeMutableState,
                    onNavigateUp = {
                        menuScreenViewModel.navigateUp()
                    }
                ) { isDarkMode ->
                    onCheckChangedDarkMode(isDarkMode)
                    menuScreenViewModel.toggleDarkMode(isDarkMode)
                    menuScreenViewModel.saveDarkMode(isDarkMode)
                }
            }

            composable<Destination.TaskScreen> {
                val taskScreenViewModel = koinInject<TaskScreenViewModel>()
                TaskScreen(
                    taskName = taskScreenViewModel.taskName,
                    taskDescription = taskScreenViewModel.taskDescription,
                    taskNameErrorMessage = taskScreenViewModel.taskNameErrorMessage,
                    taskDescriptionErrorMessage = taskScreenViewModel.taskDescriptionErrorMessage,
                    snackbarHostState = taskScreenViewModel.snackbarHostState,
                    onNavigateUp = {
                        taskScreenViewModel.navigateUp()
                    },
                    onTaskNameValueChanged = { value ->
                        if (taskScreenViewModel.taskNameErrorMessage.value.isNotBlank()) {
                            taskScreenViewModel.taskNameErrorMessage.value = ""
                        }
                        taskScreenViewModel.taskName.value = value
                    },
                    onTaskDescriptionValueChanged = { value ->
                        if (taskScreenViewModel.taskDescriptionErrorMessage.value.isNotBlank()) {
                            taskScreenViewModel.taskDescriptionErrorMessage.value = ""
                        }
                        taskScreenViewModel.taskDescription.value = value
                    }
                ) {
                    taskScreenViewModel.validInputs()
                }
            }
        }
    }
}