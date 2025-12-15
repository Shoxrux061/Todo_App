package uz.test.todo.domain.use_case

import uz.test.todo.domain.model.tasks.TaskModel
import uz.test.todo.domain.repository.TasksRepository
import javax.inject.Inject

class GetTaskByIdUseCase @Inject constructor(
    private val repository: TasksRepository
) {

    suspend operator fun invoke(id: Int): TaskModel {
        return repository.getTaskById(id)
    }

}