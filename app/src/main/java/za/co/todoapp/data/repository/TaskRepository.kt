package za.co.todoapp.data.repository

import za.co.todoapp.common.utilities.Resource
import za.co.todoapp.data.dataSource.TaskLocalDataSource
import za.co.todoapp.data.model.Task

class TaskRepository(
    private val taskLocalDataSource: TaskLocalDataSource
) {
    suspend fun getAllByCompleteStatus(isComplete: Boolean): Resource<List<Task>> {
        return taskLocalDataSource.getAllByCompleteStatus(isComplete)
    }

    suspend fun upsert(task: Task): Resource<Task> {
        return taskLocalDataSource.upsert(task)
    }

    suspend fun delete(task: Task): Resource<Int> {
        return taskLocalDataSource.delete(task)
    }
}