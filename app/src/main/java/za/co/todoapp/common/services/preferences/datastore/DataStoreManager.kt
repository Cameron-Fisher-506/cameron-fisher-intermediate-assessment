package za.co.todoapp.common.services.preferences.datastore

import android.app.Application
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class DataStoreManager(
    private val application: Application
) {
    private val dataStore: DataStore<Preferences> = application.createDataStore(name = "todoapp_preferences")

    companion object {
        val DARK_MODE_KEY: Preferences.Key<Boolean> = preferencesKey<Boolean>("IS_DARK_MODE")
    }

    suspend fun saveDarkMode(value: Boolean) {
        dataStore.edit {
            it[DARK_MODE_KEY] = value
        }
    }

    val isDarkMode: Flow<Boolean> = dataStore.data.map {
        it[DARK_MODE_KEY] ?: false
    }
}