package za.co.todoapp.presentation.menu

import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import za.co.todoapp.common.services.navigation.Navigator
import za.co.todoapp.common.services.preferences.sharedPreferences.SharedPreferencesManager
import za.co.todoapp.presentation.BaseViewModel

class MenuScreenViewModel(
    private val navigator: Navigator,
    private val sharedPreferencesManager: SharedPreferencesManager
): BaseViewModel() {
    val isDarkModeMutableState = mutableStateOf(false);

    init {
        isDarkModeMutableState.value = sharedPreferencesManager.isDarkMode()
    }

    fun toggleDarkMode(isDarkMode: Boolean) {
        isDarkModeMutableState.value = isDarkMode
    }

    fun saveDarkMode(isDarkMode: Boolean) {
        sharedPreferencesManager.saveIsDarkMode(isDarkMode)
    }

    fun navigateUp() = CoroutineScope(Dispatchers.IO).launch {
        navigator.navigateUp()
    }
}