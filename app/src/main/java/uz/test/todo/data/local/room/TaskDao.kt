package uz.test.todo.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Insert
    suspend fun addTask(taskEntity: TaskEntity): Long

    @Update
    suspend fun updateTask(taskEntity: TaskEntity): Int

    @Query("DELETE FROM tasks WHERE taskId = :id")
    suspend fun deleteTaskById(id: Int): Int

    @Query("SELECT * FROM tasks WHERE taskId = :id")
    suspend fun getTaskById(id: Int): TaskEntity?
}