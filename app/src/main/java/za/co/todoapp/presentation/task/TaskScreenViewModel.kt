package za.co.todoapp.presentation.task

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import za.co.todoapp.common.enum.Status
import za.co.todoapp.common.services.navigation.Destination
import za.co.todoapp.common.services.navigation.Navigator
import za.co.todoapp.data.model.Task
import za.co.todoapp.domain.SaveOrUpdateTaskUseCase
import za.co.todoapp.presentation.home.HomeScreenViewModel.TaskState

class TaskScreenViewModel(
    private val navigator: Navigator,
    private val saveOrUpdateTaskUseCase: SaveOrUpdateTaskUseCase
) {
    data class TaskState(
        val isLoading: Boolean = false,
        val taskList: List<Task> = emptyList(),
        val errorMessage: String = ""
    )

    private val taskMutableState = mutableStateOf(TaskState())
    val taskState: State<TaskState> = taskMutableState

    val taskName = mutableStateOf("")
    val taskDescription = mutableStateOf("")

    fun navigateUp() = CoroutineScope(Dispatchers.IO).launch {
        navigator.navigateUp()
    }

    fun saveOrUpdateTask(task: Task) {
        saveOrUpdateTaskUseCase(task).onEach { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    val data = resource.data
                    if (data != null) {
                        taskMutableState.value = TaskState(taskList = listOf(data))
                    } else {
                        taskMutableState.value = TaskState(errorMessage = "Task not saved.")
                    }
                }

                Status.ERROR -> {
                    taskMutableState.value = TaskState(errorMessage = "Task not saved.")
                }

                Status.LOADING -> {
                    taskMutableState.value = TaskState(isLoading = true)
                }
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }
}