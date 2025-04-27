package za.co.todoapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import za.co.todoapp.common.services.navigation.Navigation
import za.co.todoapp.ui.theme.TodoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val darkTheme = remember { mutableStateOf(false) }
            TodoAppTheme(darkTheme = darkTheme.value) {
                Navigation { isDarkMode ->
                    darkTheme.value = isDarkMode
                }
            }
        }
    }
}
