package za.co.todoapp.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import za.co.todoapp.common.utilities.Resource
import za.co.todoapp.data.model.entity.TaskEntity
import za.co.todoapp.data.repository.TaskRepository

class SaveOrUpdateTaskUseCase(
    private val taskRepository: TaskRepository
) {
    operator fun invoke(taskEntity: TaskEntity): Flow<Resource<TaskEntity>> = flow {
        emit(Resource.loading())
        emit(taskRepository.upsert(taskEntity))
    }
}