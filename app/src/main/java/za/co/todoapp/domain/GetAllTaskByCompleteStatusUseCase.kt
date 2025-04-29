package za.co.todoapp.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import za.co.todoapp.common.utilities.Resource
import za.co.todoapp.data.model.Task
import za.co.todoapp.data.repository.TaskRepository

class GetAllTaskByCompleteStatusUseCase(
    private val taskRepository: TaskRepository
) {
    operator fun invoke(isComplete: Boolean): Flow<Resource<List<Task>>> = flow {
        emit(Resource.loading())
        emit(taskRepository.getAllByCompleteStatus(isComplete))
    }
}