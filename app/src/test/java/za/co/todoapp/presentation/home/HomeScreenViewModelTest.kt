package za.co.todoapp.presentation.home

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import za.co.todoapp.common.services.location.LocationManager
import za.co.todoapp.common.services.navigation.Navigator
import za.co.todoapp.common.services.preferences.sharedPreferences.SharedPreferencesManager
import za.co.todoapp.common.services.resource.ResourceManager
import za.co.todoapp.data.model.Task
import za.co.todoapp.domain.DeleteTaskUseCase
import za.co.todoapp.domain.FetchTodayWeatherForecastUseCase
import za.co.todoapp.domain.GetAllTaskByCompleteStatusUseCase
import za.co.todoapp.domain.SaveOrUpdateTaskUseCase

class HomeScreenViewModelTest {
    private val navigator = mockk<Navigator>()
    private val locationManager = mockk<LocationManager>()
    private val resourceManager = mockk<ResourceManager>()
    private val sharedPreferencesManager = mockk<SharedPreferencesManager>()
    private val getAllTaskByCompleteStatusUseCase = mockk<GetAllTaskByCompleteStatusUseCase>()
    private val deleteTaskUseCase = mockk<DeleteTaskUseCase>()
    private val saveOrUpdateTaskUseCase = mockk<SaveOrUpdateTaskUseCase>()
    private val fetchTodayWeatherForecastUseCase = mockk<FetchTodayWeatherForecastUseCase>()
    private val homeScreenViewModel = HomeScreenViewModel(navigator, locationManager, resourceManager, sharedPreferencesManager, getAllTaskByCompleteStatusUseCase, deleteTaskUseCase, saveOrUpdateTaskUseCase, fetchTodayWeatherForecastUseCase)

    @Test
    @DisplayName("Get Tab Item List")
    fun shouldReturnListWhenGetTabItemListIsCalled() {
        val tabItemList = homeScreenViewModel.getTabItemList()
        assertTrue(tabItemList.size == 2)
        assertEquals("To do", tabItemList.first().title)
        assertEquals("Completed", tabItemList.last().title)
    }

    @Test
    @DisplayName("Is Dark Mode - True")
    fun shouldReturnTrueWhenIsDarkModeIsCalled() {
        every { sharedPreferencesManager.isDarkMode() } returns true

        val isDarkMode = homeScreenViewModel.isDarkMode()

        assertTrue(isDarkMode)
    }

    @Test
    @DisplayName("Is Dark Mode - False")
    fun shouldReturnFalseWhenIsDarkModeIsCalled() {
        every { sharedPreferencesManager.isDarkMode() } returns false

        val isDarkMode = homeScreenViewModel.isDarkMode()

        assertFalse(isDarkMode)
    }

    @Test
    @DisplayName("Get All Tasks By Complete Status")
    fun shouldCallGetAllTasksByCompleteStatusUseCaseWhenGetAllTasksByCompleteStatusIsCalled() {
        every { getAllTaskByCompleteStatusUseCase(any<Boolean>()) } returns flow {}

        homeScreenViewModel.getAllTaskByCompleteStatus(false)

        verify(exactly = 1) { getAllTaskByCompleteStatusUseCase.invoke(eq(false)) }
    }

    @Test
    @DisplayName("Delete Task")
    fun shouldCallDeleteTaskUseCaseWhenDeleteTaskIsCalled() {
        every { deleteTaskUseCase(any<Task>()) } returns flow {}

        homeScreenViewModel.deleteTask(Task())

        verify(exactly = 1) { deleteTaskUseCase.invoke(any<Task>()) }
    }

    @Test
    @DisplayName("Update Task Complete Status")
    fun shouldCallSaveOrUpdateUseCaseWhenUpdateTaskCompleteStatusIsCalled() {
        every { saveOrUpdateTaskUseCase(any<Task>()) } returns flow {}

        homeScreenViewModel.updateTaskCompleteStatus(Task())

        verify(exactly = 1) { saveOrUpdateTaskUseCase.invoke(any<Task>()) }
    }

    @Test
    @DisplayName("Fetch Today Weather Forecast")
    fun shouldCallFetchTodayWeatherForecastUseCaseWhenFetchTodayWeatherForecastIsCalled() {
        every { fetchTodayWeatherForecastUseCase(any<Double>(), any<Double>()) } returns flow {}

        homeScreenViewModel.fetchTodayWeatherForecast(0.0, 0.0)

        verify(exactly = 1) { fetchTodayWeatherForecastUseCase.invoke(eq(0.0), eq(0.0)) }
    }
}