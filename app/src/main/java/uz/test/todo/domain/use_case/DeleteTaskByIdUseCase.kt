package uz.test.todo.domain.use_case

import uz.test.todo.domain.repository.TasksRepository
import javax.inject.Inject

class DeleteTaskByIdUseCase @Inject constructor(
    private val repository: TasksRepository
) {

    suspend operator fun invoke(id: Int): Result<Boolean> {
        return repository.deleteTask(id)
    }

}