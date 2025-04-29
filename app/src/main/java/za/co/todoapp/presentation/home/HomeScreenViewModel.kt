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
import za.co.todoapp.R
import za.co.todoapp.common.enum.Status
import za.co.todoapp.common.services.location.LocationManager
import za.co.todoapp.common.services.navigation.Destination
import za.co.todoapp.common.services.navigation.Navigator
import za.co.todoapp.common.services.preferences.sharedPreferences.SharedPreferencesManager
import za.co.todoapp.common.services.resource.ResourceManager
import za.co.todoapp.data.model.Task
import za.co.todoapp.data.model.currentWeather.CurrentWeatherResponse
import za.co.todoapp.domain.DeleteTaskUseCase
import za.co.todoapp.domain.FetchTodayWeatherForecastUseCase
import za.co.todoapp.domain.GetAllTaskByCompleteStatusUseCase
import za.co.todoapp.domain.SaveOrUpdateTaskUseCase
import za.co.todoapp.presentation.BaseViewModel

class HomeScreenViewModel(
    private val navigator: Navigator,
    private val locationManager: LocationManager,
    private val resourceManager: ResourceManager,
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val getAllTaskByCompleteStatusUseCase: GetAllTaskByCompleteStatusUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val saveOrUpdateTaskUseCase: SaveOrUpdateTaskUseCase,
    private val fetchTodayWeatherForecastUseCase: FetchTodayWeatherForecastUseCase
) : BaseViewModel() {
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

    data class DeleteTaskState(
        val isLoading: Boolean = false,
        val isDeleted: Int = 0,
        val errorMessage: String = ""
    )

    private val deleteTaskMutableState = mutableStateOf(DeleteTaskState())
    val deleteTaskState: State<DeleteTaskState> = deleteTaskMutableState

    data class CurrentWeatherState(
        val isLoading: Boolean = false,
        val currentWeatherResponse: CurrentWeatherResponse = CurrentWeatherResponse(),
        val errorMessage: String = ""
    )

    private val currentWeatherMutableState = mutableStateOf(CurrentWeatherState())
    val currentWeatherState: State<CurrentWeatherState> = currentWeatherMutableState

    fun navigateToTaskScreen() = CoroutineScope(Dispatchers.IO).launch {
        navigator.navigate(destination = Destination.TaskScreen)
    }

    fun navigateToMenuScreen() = CoroutineScope(Dispatchers.IO).launch {
        navigator.navigate(destination = Destination.MenuScreen)
    }

    fun getTabItemList(): List<TabItem> = listOf(
        TabItem(
            title = resourceManager.getString(R.string.todo_todo),
            unselectedIcon = Icons.Outlined.Create,
            selectedIcon = Icons.Filled.Create
        ),
        TabItem(
            title = resourceManager.getString(R.string.todo_complete),
            unselectedIcon = Icons.Outlined.CheckCircle,
            selectedIcon = Icons.Filled.CheckCircle
        )
    )

    fun getDeviceLocation(
        isLocationPermissionGranted: Boolean,
        onSuccessListener: (latitude: Double, longitude: Double) -> Unit
    ) {
        locationManager.getDeviceLocation(isLocationPermissionGranted,
            onFailureListener = {
                displaySnackbar(resourceManager.getString(R.string.todo_no_location_information_available))
            }, onPermissionDeniedListener = {
                displaySnackbar(resourceManager.getString(R.string.todo_please_grant_location_permission))
            }) { latitude, longitude ->
            onSuccessListener(latitude, longitude)
        }
    }

    fun isDarkMode(): Boolean = sharedPreferencesManager.isDarkMode()

    fun getAllTaskByCompleteStatus(isComplete: Boolean) {
        getAllTaskByCompleteStatusUseCase(isComplete).onEach { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    val data = resource.data
                    if (!data.isNullOrEmpty()) {
                        taskMutableState.value = TaskState(taskList = data)
                    } else {
                        taskMutableState.value = TaskState(errorMessage = resourceManager.getString(R.string.todo_no_tasks_available))
                    }
                }

                Status.ERROR -> {
                    taskMutableState.value = TaskState(errorMessage = resourceManager.getString(R.string.todo_no_tasks_available))
                }

                Status.LOADING -> {
                    taskMutableState.value = TaskState(isLoading = true)
                }
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }

    fun deleteTask(task: Task) {
        deleteTaskUseCase(task).onEach { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    val data = resource.data
                    if (data != null && data == 1) {
                        displaySnackbar(resourceManager.getString(R.string.todo_task_deleted_successfully))
                        deleteTaskMutableState.value = DeleteTaskState(isDeleted = data)
                        getAllTaskByCompleteStatus(task.isComplete)
                    }
                }

                Status.ERROR -> {
                    displaySnackbar(resourceManager.getString(R.string.todo_task_not_deleted))
                    deleteTaskMutableState.value =
                        DeleteTaskState(errorMessage = resourceManager.getString(R.string.todo_task_not_deleted))
                    getAllTaskByCompleteStatus(task.isComplete)
                }

                Status.LOADING -> {
                    deleteTaskMutableState.value = DeleteTaskState(isLoading = true)
                }
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }

    fun updateTaskCompleteStatus(task: Task) {
        saveOrUpdateTaskUseCase(task).onEach { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    val data = resource.data
                    if (data != null) {
                        taskMutableState.value = TaskState(taskList = listOf(data))
                        displaySnackbar(resourceManager.getString(R.string.todo_task_updated_successfully))
                        getAllTaskByCompleteStatus(!task.isComplete)
                    } else {
                        displaySnackbar(resourceManager.getString(R.string.todo_task_not_updated))
                        taskMutableState.value = TaskState(errorMessage = resourceManager.getString(R.string.todo_task_not_updated))
                    }
                }

                Status.ERROR -> {
                    displaySnackbar(resourceManager.getString(R.string.todo_task_not_updated))
                    taskMutableState.value = TaskState(errorMessage = resourceManager.getString(R.string.todo_task_not_updated))
                    getAllTaskByCompleteStatus(!task.isComplete)
                }

                Status.LOADING -> {
                    taskMutableState.value = TaskState(isLoading = true)
                }
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }

    fun fetchTodayWeatherForecast(latitude: Double, longitude: Double) {
        fetchTodayWeatherForecastUseCase(latitude, longitude).onEach { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    val data = resource.data
                    if (data != null) {
                        currentWeatherMutableState.value =
                            CurrentWeatherState(currentWeatherResponse = data)
                    }
                }

                Status.ERROR -> {
                    currentWeatherMutableState.value =
                        CurrentWeatherState(errorMessage = resourceManager.getString(R.string.todo_weather_information_not_found))
                }

                Status.LOADING -> {
                    currentWeatherMutableState.value = CurrentWeatherState(isLoading = true)
                }
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }
}