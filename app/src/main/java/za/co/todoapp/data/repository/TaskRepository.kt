package za.co.todoapp.data.repository

import za.co.todoapp.common.utilities.Resource
import za.co.todoapp.data.dataSource.TaskLocalDataSource
import za.co.todoapp.data.model.entity.TaskEntity

class TaskRepository(
    private val taskLocalDataSource: TaskLocalDataSource
) {
    suspend fun getAllByCompleteStatus(isComplete: Boolean): Resource<List<TaskEntity>> {
        return taskLocalDataSource.getAllByCompleteStatus(isComplete)
    }

    suspend fun upsert(taskEntity: TaskEntity): Resource<TaskEntity> {
        return taskLocalDataSource.upsert(taskEntity)
    }

    suspend fun delete(taskEntity: TaskEntity): Resource<Int> {
        return taskLocalDataSource.delete(taskEntity)
    }
}