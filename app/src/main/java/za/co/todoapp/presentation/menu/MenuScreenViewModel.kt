package za.co.todoapp.presentation.menu

import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import za.co.todoapp.common.services.navigation.Navigator

class MenuScreenViewModel(
    private val navigator: Navigator,
) {
    val isDarkModeMutableState = mutableStateOf(false);

    fun toggleDarkMode(isDarkMode: Boolean) {
        isDarkModeMutableState.value = isDarkMode
    }

    fun navigateUp() = CoroutineScope(Dispatchers.IO).launch {
        navigator.navigateUp()
    }
}