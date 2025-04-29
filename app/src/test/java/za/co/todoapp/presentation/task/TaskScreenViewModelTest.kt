package za.co.todoapp.presentation.task

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import za.co.todoapp.common.services.navigation.Navigator
import za.co.todoapp.common.services.resource.ResourceManager
import za.co.todoapp.data.model.Task
import za.co.todoapp.domain.SaveOrUpdateTaskUseCase

class TaskScreenViewModelTest {
    private val navigator = mockk<Navigator>()
    private val resourceManager = mockk<ResourceManager>()
    private val saveOrUpdateTaskUseCase = mockk<SaveOrUpdateTaskUseCase>()

    private lateinit var taskScreenViewModel: TaskScreenViewModel

    @BeforeEach
    fun setUp() {
        taskScreenViewModel = TaskScreenViewModel(navigator, resourceManager, saveOrUpdateTaskUseCase)
    }

    @Test
    @DisplayName("Navigate Up")
    fun shouldNavigateUpWhenNavigateUpIsCalled() {
        coEvery { navigator.navigateUp() } returns Unit

        taskScreenViewModel.navigateUp()

        coVerify(exactly = 1) {
            navigator.navigateUp()
        }
    }

    @Test
    @DisplayName("Validate Inputs - Blank Task Name")
    fun shouldDisplayBlankTaskNameStringWhenValidateInputsIsCalled() {
        val stringResource = "Please provide the task name."
        every { resourceManager.getString(any()) } returns stringResource

        taskScreenViewModel.validateInputs()

        assertEquals(stringResource, taskScreenViewModel.taskNameErrorMessage.value)
    }

    @Test
    @DisplayName("Validate Inputs - Blank Task Description")
    fun shouldDisplayBlankTaskDescriptionStringWhenValidateInputsIsCalled() {
        val stringResource = "Please provide a brief description of the task."
        every { resourceManager.getString(any()) } returns stringResource

        taskScreenViewModel.taskName.value = "Shopping"
        taskScreenViewModel.validateInputs()

        assertEquals(stringResource, taskScreenViewModel.taskDescriptionErrorMessage.value)
    }

    @Test
    @DisplayName("Validate Inputs - Save New Task")
    fun shouldCallSaveNewTaskWhenValidateInputsIsCalled() {
        every { saveOrUpdateTaskUseCase(any<Task>()) } returns flow {}

        taskScreenViewModel.taskName.value = "Shopping"
        taskScreenViewModel.taskDescription.value = "Chicken, Eggs and Bread"
        taskScreenViewModel.validateInputs()

        verify(exactly = 1) { saveOrUpdateTaskUseCase.invoke(any<Task>()) }
    }
}