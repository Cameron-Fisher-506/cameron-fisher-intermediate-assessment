package za.co.todoapp.data.model

import android.app.ActivityManager.TaskDescription

class Task(
    var title: String = "",
    var description: String = "",
    var isComplete: Boolean = false
)