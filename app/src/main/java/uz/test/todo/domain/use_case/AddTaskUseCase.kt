package uz.test.todo.domain.use_case

import uz.test.todo.domain.model.tasks.TaskModel
import uz.test.todo.domain.repository.TasksRepository
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(
    private val repository: TasksRepository
) {

    suspend operator fun invoke(task: TaskModel) = repository.addTask(task)

}