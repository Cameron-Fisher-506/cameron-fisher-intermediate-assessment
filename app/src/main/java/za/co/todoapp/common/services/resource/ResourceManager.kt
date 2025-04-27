package za.co.todoapp.common.services.resource

import android.app.Application
import androidx.annotation.StringRes

class ResourceManager(
    private val application: Application
) {
    fun getString(@StringRes stringResId: Int): String {
        return application.getString(stringResId)
    }
}