package uz.test.todo.domain.use_case

import uz.test.todo.domain.model.tasks.TaskModel
import uz.test.todo.domain.repository.TasksRepository
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(
    private val repository: TasksRepository
) {

    suspend operator fun invoke(taskModel: TaskModel): Result<Boolean> {
        return repository.updateTask(taskModel)
    }

}