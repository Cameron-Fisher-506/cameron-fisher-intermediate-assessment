package za.co.todoapp.presentation.task

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.core.component.getScopeName
import za.co.todoapp.R
import za.co.todoapp.common.enum.Status
import za.co.todoapp.common.services.navigation.Navigator
import za.co.todoapp.common.services.resource.ResourceManager
import za.co.todoapp.data.model.Task
import za.co.todoapp.domain.SaveOrUpdateTaskUseCase
import za.co.todoapp.presentation.BaseViewModel

class TaskScreenViewModel(
    private val navigator: Navigator,
    private val resourceManager: ResourceManager,
    private val saveOrUpdateTaskUseCase: SaveOrUpdateTaskUseCase
): BaseViewModel() {
    data class TaskState(
        val isLoading: Boolean = false,
        val taskList: List<Task> = emptyList(),
        val errorMessage: String = ""
    )

    private val taskMutableState = mutableStateOf(TaskState())
    val taskState: State<TaskState> = taskMutableState

    val taskName = mutableStateOf("")
    val taskDescription = mutableStateOf("")
    val taskNameErrorMessage = mutableStateOf("")
    val taskDescriptionErrorMessage = mutableStateOf("")

    fun navigateUp() = CoroutineScope(Dispatchers.IO).launch {
        navigator.navigateUp()
    }

    fun validInputs() {
        when {
            taskName.value.isBlank() -> {
                taskNameErrorMessage.value = resourceManager.getString(R.string.todo_please_provide_the_task_name)
            }
            taskDescription.value.isBlank() -> {
                taskDescriptionErrorMessage.value = resourceManager.getString(R.string.todo_please_provide_a_brief_description)
            }
            else -> {
                saveNewTask(Task().apply {
                    title = taskName.value
                    description = taskDescription.value
                })
            }
        }
    }

    private fun saveNewTask(task: Task) {
        saveOrUpdateTaskUseCase(task).onEach { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    val data = resource.data
                    if (data != null) {
                        taskMutableState.value = TaskState(taskList = listOf(data))
                        displaySnackbar("Task saved successfully.")
                    } else {
                        displaySnackbar("Task not saved.")
                        taskMutableState.value = TaskState(errorMessage = "Task not saved.")
                    }
                }

                Status.ERROR -> {
                    displaySnackbar("Task not saved.")
                    taskMutableState.value = TaskState(errorMessage = "Task not saved.")
                }

                Status.LOADING -> {
                    taskMutableState.value = TaskState(isLoading = true)
                }
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }
}