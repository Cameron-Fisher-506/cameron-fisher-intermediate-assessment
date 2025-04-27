package za.co.todoapp.data.dataSource

import android.app.Application
import za.co.todoapp.common.utilities.Resource
import za.co.todoapp.data.local.ApplicationDatabase
import za.co.todoapp.data.local.ITaskDao
import za.co.todoapp.data.local.upsert
import za.co.todoapp.data.model.Task

class TaskLocalDataSource(application: Application) {
    private val taskDao: ITaskDao = ApplicationDatabase.getDatabase(application).taskDao()

    suspend fun getAllByCompleteStatus(isComplete: Boolean): Resource<List<Task>> {
        return ApplicationDatabase.getResource { taskDao.getAllByCompleteStatus(isComplete) }
    }

    suspend fun upsert(task: Task): Resource<Task> {
        return ApplicationDatabase.getResource { taskDao.upsert(task, taskDao) }
    }
}