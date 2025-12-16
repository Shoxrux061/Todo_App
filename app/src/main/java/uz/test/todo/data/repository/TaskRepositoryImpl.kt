package uz.test.todo.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import uz.test.todo.core.utils.safeDatabaseCall
import uz.test.todo.data.local.room.TaskDao
import uz.test.todo.data.mapper.toData
import uz.test.todo.data.mapper.toDomain
import uz.test.todo.domain.model.tasks.TaskModel
import uz.test.todo.domain.repository.TasksRepository
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val dao: TaskDao
) : TasksRepository {

    override fun getAllTasks(): Flow<Result<List<TaskModel>>> =
        dao.getAllTasks().map { list ->
            safeDatabaseCall { list.map { it.toDomain() } }
        }

    override suspend fun getTaskById(id: Int): Result<TaskModel> {
        return safeDatabaseCall {
            dao.getTaskById(id)?.toDomain()
                ?: TaskModel()
        }
    }

    override suspend fun updateTask(taskModel: TaskModel): Result<Boolean> {
        return safeDatabaseCall {
            dao.updateTask(taskModel.toData()) > 0
        }
    }

    override suspend fun deleteTask(id: Int): Result<Boolean> {
        return safeDatabaseCall {
            dao.deleteTaskById(id) > 0
        }
    }

    override suspend fun addTask(task: TaskModel): Result<Boolean> {
        return safeDatabaseCall {
            dao.addTask(task.toData()) > 0
        }
    }
}