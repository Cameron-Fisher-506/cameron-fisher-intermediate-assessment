package za.co.todoapp.presentation.menu

import androidx.compose.runtime.mutableStateOf

class MenuScreenViewModel {
    val isDarkModeMutableState = mutableStateOf(false);

    fun toggleDarkMode(isDarkMode: Boolean) {
        isDarkModeMutableState.value = isDarkMode
    }
}