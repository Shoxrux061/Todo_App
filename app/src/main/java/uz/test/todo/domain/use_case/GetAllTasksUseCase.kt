package uz.test.todo.domain.use_case

import kotlinx.coroutines.flow.Flow
import uz.test.todo.domain.model.tasks.TaskModel
import uz.test.todo.domain.repository.TasksRepository
import javax.inject.Inject

class GetAllTasksUseCase @Inject constructor(
    private val repository: TasksRepository
) {

    operator fun invoke(): Flow<List<TaskModel>> {
        return repository.getAllTasks()
    }
}