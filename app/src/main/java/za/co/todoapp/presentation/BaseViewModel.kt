package za.co.todoapp.presentation

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel {
    val snackbarHostState = SnackbarHostState()

    fun displaySnackbar(message: String) = CoroutineScope(Dispatchers.IO).launch {
        snackbarHostState.showSnackbar(
            message = message,
            withDismissAction = true,
            duration = SnackbarDuration.Long
        )
    }
}