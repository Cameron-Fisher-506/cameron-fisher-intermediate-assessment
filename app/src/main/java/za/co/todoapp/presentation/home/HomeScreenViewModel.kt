package za.co.todoapp.presentation.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Create
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import za.co.todoapp.common.enum.Status
import za.co.todoapp.common.services.navigation.Destination
import za.co.todoapp.common.services.navigation.Navigator
import za.co.todoapp.data.model.Task
import za.co.todoapp.domain.GetAllTaskByCompleteStatusUseCase

class HomeScreenViewModel(
    private val navigator: Navigator,
    private val getAllTaskByCompleteStatusUseCase: GetAllTaskByCompleteStatusUseCase
) {
    data class TabItem(
        val title: String,
        val unselectedIcon: ImageVector,
        val selectedIcon: ImageVector
    )

    data class TaskState(
        val isLoading: Boolean = false,
        val taskList: List<Task> = emptyList(),
        val errorMessage: String = ""
    )

    private val taskMutableState = mutableStateOf(TaskState())
    val taskState: State<TaskState> = taskMutableState

    fun navigateToTaskScreen() = CoroutineScope(Dispatchers.IO).launch {
        navigator.navigate(destination = Destination.TaskScreen)
    }

    fun navigateToMenuScreen() = CoroutineScope(Dispatchers.IO).launch {
        navigator.navigate(destination = Destination.MenuScreen)
    }

    fun getTabItemList(): List<TabItem> = listOf(
        TabItem(
            title = "To do",
            unselectedIcon = Icons.Outlined.Create,
            selectedIcon = Icons.Filled.Create
        ),
        TabItem(
            title = "Completed",
            unselectedIcon = Icons.Outlined.CheckCircle,
            selectedIcon = Icons.Filled.CheckCircle
        )
    )

    fun getAllTaskByCompleteStatus(isComplete: Boolean) {
        getAllTaskByCompleteStatusUseCase(isComplete).onEach { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    val data = resource.data
                    if (!data.isNullOrEmpty()) {
                        taskMutableState.value = TaskState(taskList = data)
                    } else {
                        taskMutableState.value = TaskState(errorMessage = "No tasks available.")
                    }
                }

                Status.ERROR -> {
                    taskMutableState.value = TaskState(errorMessage = "No tasks available.")
                }

                Status.LOADING -> {
                    taskMutableState.value = TaskState(isLoading = true)
                }
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }
}