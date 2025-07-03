package za.co.todoapp.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import za.co.todoapp.common.utilities.Resource
import za.co.todoapp.data.model.entity.TaskEntity
import za.co.todoapp.data.repository.TaskRepository

class GetAllTaskByCompleteStatusUseCase(
    private val taskRepository: TaskRepository
) {
    operator fun invoke(isComplete: Boolean): Flow<Resource<List<TaskEntity>>> = flow {
        emit(Resource.loading())
        emit(taskRepository.getAllByCompleteStatus(isComplete))
    }
}