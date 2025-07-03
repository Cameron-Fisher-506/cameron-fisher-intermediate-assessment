package za.co.todoapp.data.dataSource

import android.app.Application
import za.co.todoapp.common.utilities.Resource
import za.co.todoapp.data.local.ApplicationDatabase
import za.co.todoapp.data.local.ITaskDao
import za.co.todoapp.data.local.upsert
import za.co.todoapp.data.model.entity.TaskEntity

class TaskLocalDataSource(application: Application) {
    private val taskDao: ITaskDao = ApplicationDatabase.getDatabase(application).taskDao()

    suspend fun getAllByCompleteStatus(isComplete: Boolean): Resource<List<TaskEntity>> {
        return ApplicationDatabase.getResource { taskDao.getAllByCompleteStatus(isComplete) }
    }

    suspend fun upsert(taskEntity: TaskEntity): Resource<TaskEntity> {
        return ApplicationDatabase.getResource { taskDao upsert taskEntity }
    }

    suspend fun delete(taskEntity: TaskEntity): Resource<Int> {
        return ApplicationDatabase.getResource { taskDao.delete(taskEntity) }
    }
}