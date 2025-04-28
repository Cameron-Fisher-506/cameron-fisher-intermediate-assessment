package za.co.todoapp.common.services.preferences.sharedPreferences

import android.app.Application
import android.content.SharedPreferences


class SharedPreferencesManager(
    private val application: Application
) {
    private val sharedPreferencesName = "todoAppPreferences"

    companion object {
        const val DARK_MODE: String = "DARK_MODE"
    }

    fun saveIsDarkMode(value: Boolean) {
        application.getSharedPreferences(sharedPreferencesName, 0).edit().apply {
            putBoolean(DARK_MODE, value)
            apply()
        }
    }

    fun isDarkMode(): Boolean {
        val sharedPreferences: SharedPreferences = application.getSharedPreferences(sharedPreferencesName, 0)
        return sharedPreferences.getBoolean(DARK_MODE, false)
    }
}