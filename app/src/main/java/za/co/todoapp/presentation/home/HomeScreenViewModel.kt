package za.co.todoapp.presentation.home

import androidx.navigation.Navigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import za.co.todoapp.common.services.navigation.Destination
import za.co.todoapp.common.services.navigation.Navigator

class HomeScreenViewModel(
    private val navigator: Navigator
) {
    fun navigateToTaskScreen() = CoroutineScope(Dispatchers.IO).launch {
        navigator.navigate(destination = Destination.TaskScreen)
    }

    fun navigateToMenuScreen() = CoroutineScope(Dispatchers.IO).launch {
        navigator.navigate(destination = Destination.MenuScreen)
    }
}