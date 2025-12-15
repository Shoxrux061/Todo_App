package uz.test.todo.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.test.todo.domain.model.tasks.TaskModel

interface TasksRepository {

    fun getAllTasks(): Flow<List<TaskModel>>

    suspend fun getTaskById(id: Int): TaskModel

    suspend fun updateTask(taskModel: TaskModel): Boolean

    suspend fun deleteTask(id: Int): Boolean

}