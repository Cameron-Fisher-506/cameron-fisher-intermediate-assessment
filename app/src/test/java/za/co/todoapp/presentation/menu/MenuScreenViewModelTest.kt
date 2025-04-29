package za.co.todoapp.presentation.menu

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import za.co.todoapp.common.services.navigation.Navigator
import za.co.todoapp.common.services.preferences.sharedPreferences.SharedPreferencesManager

class MenuScreenViewModelTest {
    private val navigator = mockk<Navigator>()
    private val sharedPreferencesManager = mockk<SharedPreferencesManager>()

    private lateinit var menuScreenViewModel: MenuScreenViewModel

    @BeforeEach
    fun setUp() {
        every { sharedPreferencesManager.isDarkMode() } returns false
        menuScreenViewModel = MenuScreenViewModel(navigator, sharedPreferencesManager)
    }

    @Test
    @DisplayName("Save Dark Mode - False")
    fun shouldSaveFalseWhenSaveDarkModeIsCalled() {
        every { sharedPreferencesManager.saveIsDarkMode(any<Boolean>()) } returns Unit

        menuScreenViewModel.saveDarkMode(false)

        verify(exactly = 1) {
            sharedPreferencesManager.saveIsDarkMode(eq(false))
        }
    }

    @Test
    @DisplayName("Save Dark Mode - True")
    fun shouldSaveTrueWhenSaveDarkModeIsCalled() {
        every { sharedPreferencesManager.saveIsDarkMode(any<Boolean>()) } returns Unit

        menuScreenViewModel.saveDarkMode(true)

        verify(exactly = 1) {
            sharedPreferencesManager.saveIsDarkMode(eq(true))
        }
    }

    @Test
    @DisplayName("Navigate Up")
    fun shouldNavigateUpWhenNavigateUpIsCalled() {
        coEvery { navigator.navigateUp() } returns Unit

        menuScreenViewModel.navigateUp()

        coVerify(exactly = 1) {
            navigator.navigateUp()
        }
    }

    @Test
    @DisplayName("Toggle Dark Mode - False")
    fun shouldSaveFalseWhenToggleDarkModeIsCalled() {
        menuScreenViewModel.toggleDarkMode(false)
        assertFalse(menuScreenViewModel.isDarkModeMutableState.value)
    }

    @Test
    @DisplayName("Toggle Dark Mode - True")
    fun shouldSaveTrueWhenToggleDarkModeIsCalled() {
        menuScreenViewModel.toggleDarkMode(true)
        assertTrue(menuScreenViewModel.isDarkModeMutableState.value)
    }
}