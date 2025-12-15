package uz.test.todo.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import uz.test.todo.data.local.room.TaskDao
import uz.test.todo.data.mapper.toData
import uz.test.todo.data.mapper.toDomain
import uz.test.todo.domain.model.tasks.TaskModel
import uz.test.todo.domain.repository.TasksRepository
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val dao: TaskDao
) : TasksRepository {

    override fun getAllTasks(): Flow<List<TaskModel>> =
        dao.getAllTasks().map { list ->
            list.map { it.toDomain() }
        }

    override suspend fun getTaskById(id: Int): TaskModel {
        return dao.getTaskById(id)
            ?.toDomain()
            ?: throw IllegalStateException("Task with id=$id not found")
    }


    override suspend fun updateTask(taskModel: TaskModel): Boolean {
        return dao.updateTask(taskModel.toData()) > 0
    }

    override suspend fun deleteTask(id: Int): Boolean {
        return dao.deleteTaskById(id) > 0
    }

}