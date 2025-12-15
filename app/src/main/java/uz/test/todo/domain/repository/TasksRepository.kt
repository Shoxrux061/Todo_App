package uz.test.todo.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.test.todo.domain.model.tasks.TaskModel

interface TasksRepository {

    fun getAllTasks(): Flow<Result<List<TaskModel>>>

    suspend fun getTaskById(id: Int): Result<TaskModel>

    suspend fun updateTask(taskModel: TaskModel): Result<Boolean>

    suspend fun deleteTask(id: Int): Result<Boolean>

}