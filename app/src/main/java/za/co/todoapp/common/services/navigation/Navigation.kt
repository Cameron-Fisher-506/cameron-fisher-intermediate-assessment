package za.co.todoapp.common.services.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import org.koin.compose.koinInject
import za.co.todoapp.data.model.Task
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
                    taskState = homeScreenViewModel.taskState,
                    tabItemList = homeScreenViewModel.getTabItemList(),
                    onCreate = {
                        homeScreenViewModel.getAllTaskByCompleteStatus(false)
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
                    isDarkMode = menuScreenViewModel.isDarkModeMutableState.value,
                    onNavigateUp = {
                        menuScreenViewModel.navigateUp()
                    }
                ) {}
            }

            composable<Destination.TaskScreen> {
                val taskScreenViewModel = koinInject<TaskScreenViewModel>()
                TaskScreen(
                    taskName = taskScreenViewModel.taskName,
                    taskDescription = taskScreenViewModel.taskDescription,
                    snackbarHostState = taskScreenViewModel.snackbarHostState,
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
                    taskScreenViewModel.saveOrUpdateTask(Task().apply {
                        title = taskScreenViewModel.taskName.value
                        description = taskScreenViewModel.taskDescription.value
                    })
                }
            }
        }
    }
}